package com.cloud.payment.interceptor.server;

import com.cloud.payment.audit.GRpcAuditLog;
import com.cloud.payment.exception.GRpcException;
import io.grpc.ForwardingServerCall;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.time.Instant;
import java.util.Objects;

import static com.cloud.payment.audit.GRpcAuditLogHelper.CALLED_BY;
import static com.cloud.payment.audit.GRpcAuditLogHelper.SPAN_ID;
import static com.cloud.payment.audit.GRpcAuditLogHelper.TRACE_ID;
import static com.cloud.payment.audit.GRpcAuditLogHelper.builderBody;
import static com.cloud.payment.audit.GRpcAuditLogHelper.registerGrpcAuditLog;

@Slf4j
public class GrpcServerInterceptor implements ServerInterceptor {
    private final String applicationName;

    public GrpcServerInterceptor(final String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall,
                                                                 Metadata metadata,
                                                                 ServerCallHandler<ReqT, RespT> serverCallHandler) {
        try {
            final String traceId = metadata.get(Metadata.Key.of(TRACE_ID,
                    Metadata.ASCII_STRING_MARSHALLER));
            final String spanId = metadata.get(Metadata.Key.of(SPAN_ID,
                    Metadata.ASCII_STRING_MARSHALLER));
            final String calledBy = metadata.get(Metadata.Key.of(CALLED_BY,
                    Metadata.ASCII_STRING_MARSHALLER));

            MDC.put(TRACE_ID, traceId);
            MDC.put(SPAN_ID, spanId);
            GRpcAuditLog auditLog =
                    GRpcAuditLog.builder().traceId(traceId).spanId(spanId).serviceName(applicationName)
                            .calledBy(calledBy).method(serverCall.getMethodDescriptor().getFullMethodName())
                            .metadata(metadata).build();
            return new WrapperAuditLog<>(serverCall, metadata, serverCallHandler, auditLog);
        } finally {
            MDC.clear();
        }
    }

    // wrapper for GrpcAuditLog instance
    private static class WrapperAuditLog<ReqT, RespT> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {
        private final ServerCall<ReqT, RespT> serverCall;
        private final Metadata metadata;
        private final GRpcAuditLog grpcAuditLog;
        private final Instant startTimestamp;


        protected WrapperAuditLog(ServerCall<ReqT, RespT> serverCall,
                                  final Metadata metadata,
                                  final ServerCallHandler<ReqT, RespT> serverCallHandler,
                                  final GRpcAuditLog grpcAuditLog) {
            super(serverCallHandler.startCall(new WrapperListener<>(serverCall, grpcAuditLog), metadata));
            this.serverCall = serverCall;
            this.metadata = metadata;
            this.grpcAuditLog = grpcAuditLog;
            this.startTimestamp = Instant.now();
        }

        @Override
        public void onMessage(ReqT message) {
            MDC.put(TRACE_ID, grpcAuditLog.getTraceId());
            MDC.put(SPAN_ID, grpcAuditLog.getSpanId());
            try {
                if (Objects.nonNull(message)) {
                    grpcAuditLog.setRequestBody(builderBody(String.valueOf(message)));
                }
                super.onMessage(message);
            } finally {
                MDC.clear();
            }
        }

        @Override
        public void onHalfClose() {
            MDC.put(TRACE_ID, grpcAuditLog.getTraceId());
            MDC.put(SPAN_ID, grpcAuditLog.getSpanId());

            try {
                grpcAuditLog.setCode(Status.Code.OK);
                super.onHalfClose();
            } catch (final GRpcException ex) {
                log.error("Error grpc server: ", ex);
                grpcAuditLog.setCode(ex.getCode());
                grpcAuditLog.setResponseBody(builderBody(ex.getMessage()));
                final Status status =
                        Status.fromCode(ex.getCode()).withDescription(ex.getMessage())
                                .augmentDescription(ex.getCodeMessage()).withCause(ex.getCause());
                serverCall.close(status, metadata);
            } catch (final Exception ex) {
                log.error("Error grpc server: ", ex);
                grpcAuditLog.setCode(Status.INTERNAL.getCode());
                grpcAuditLog.setResponseBody(ex.getMessage());
                final var status =
                        Status.INTERNAL.withDescription(ex.getMessage()).augmentDescription(
                                "INTERNAL_500").withCause(ex.getCause());
                serverCall.close(status, metadata);
            } finally {
                MDC.clear();
            }
        }

        @Override
        public void onComplete() {
            MDC.put(TRACE_ID, grpcAuditLog.getTraceId());
            MDC.put(SPAN_ID, grpcAuditLog.getSpanId());
            try {
                registerGrpcAuditLog(grpcAuditLog, startTimestamp);
                super.onComplete();
            } finally {
                MDC.clear();
            }
        }

        // more actions can be added and implemented here

        @Override
        public void onReady() {
            super.onReady();
        }

        @Override
        public void onCancel() {
            super.onCancel();
        }
    }

    private static class WrapperListener<ReqT, RespT> extends ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT> {
        private final GRpcAuditLog grpcAuditLog;

        protected WrapperListener(final ServerCall<ReqT, RespT> serverCall,
                                  final GRpcAuditLog grpcAuditLog) {
            super(serverCall);
            this.grpcAuditLog = grpcAuditLog;
        }

        @Override
        public void sendMessage(RespT message) {
            if (Objects.nonNull(message)) {
                // wrap the sending message to the audit log only when it is non-null
                grpcAuditLog.setRequestBody(builderBody(String.valueOf(message)));
            }
            super.sendMessage(message);
        }
    }
}

