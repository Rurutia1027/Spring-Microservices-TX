package com.cloud.payment.interceptor.client;

import com.cloud.payment.audit.GRpcAuditLog;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;

import java.time.Instant;
import java.util.Objects;

import static com.cloud.payment.audit.GRpcAuditLogHelper.CALLED_BY;
import static com.cloud.payment.audit.GRpcAuditLogHelper.SPAN_ID;
import static com.cloud.payment.audit.GRpcAuditLogHelper.TRACE_ID;
import static com.cloud.payment.audit.GRpcAuditLogHelper.builderBody;
import static com.cloud.payment.audit.GRpcAuditLogHelper.registerGrpcAuditLog;
import static java.util.Objects.nonNull;

public class GrpcClientInterceptor implements ClientInterceptor {
    private final String gRpcClientName;
    private final String gRpcServerName;
    private final Tracer tracer;

    public GrpcClientInterceptor(final String gRPCClientName, final String gRPCServerName,
                                 final Tracer tracer) {
        this.gRpcClientName = gRPCClientName;
        this.gRpcServerName = gRPCServerName;
        this.tracer = tracer;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor,
                                                               CallOptions callOptions, Channel channel) {
        TraceContext traceContext = Objects.requireNonNull(tracer.currentSpan()).context();
        var traceId = traceContext.traceId();
        var spanId = traceContext.spanId();

        final var grpcAuditLog =
                GRpcAuditLog.builder().traceId(traceId)
                        .spanId(spanId)
                        .serviceName(gRpcClientName)
                        .calledBy(gRpcClientName)
                        .method(methodDescriptor.getFullMethodName())
                        .build();

        return new WrapperAuditLog<>(methodDescriptor, callOptions, channel, grpcAuditLog);
    }

    private class WrapperAuditLog<ReqT, RespT> extends ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {

        protected WrapperAuditLog(final MethodDescriptor<ReqT, RespT> methodDescriptor,
                                  final CallOptions callOptions, final Channel channel,
                                  final GRpcAuditLog grpcAuditLog) {
            super(
                    new SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) {
                        @Override
                        public void sendMessage(ReqT message) {
                            if (nonNull(message)) {
                                grpcAuditLog.setRequestBody(builderBody(String.valueOf(message)));
                            }
                            super.sendMessage(message);
                        }

                        @Override
                        public void start(Listener<RespT> responseListener, Metadata metadata) {
                            metadata.put(Metadata.Key.of(TRACE_ID, Metadata.ASCII_STRING_MARSHALLER), grpcAuditLog.getTraceId());
                            metadata.put(Metadata.Key.of(SPAN_ID, Metadata.ASCII_STRING_MARSHALLER), grpcAuditLog.getSpanId());
                            metadata.put(Metadata.Key.of(CALLED_BY,
                                    Metadata.ASCII_STRING_MARSHALLER), gRpcClientName);
                            super.start(new WrapperStart<>(responseListener, grpcAuditLog), metadata);
                        }
                    }
            );
        }
    }

    private static class WrapperStart<RespT> extends ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT> {
        private final GRpcAuditLog grpcAuditLog;
        private final Instant startTimestamp;

        protected WrapperStart(ClientCall.Listener<RespT> responseListener,
                               final GRpcAuditLog grpcAuditLog) {
            super(responseListener);
            this.grpcAuditLog = grpcAuditLog;
            this.startTimestamp = Instant.now();
        }

        @Override
        public void onMessage(RespT message) {
            if (nonNull(message)) {
                grpcAuditLog.setResponseBody(builderBody(String.valueOf(message)));
            }
            super.onMessage(message);
        }

        @Override
        public void onHeaders(Metadata headers) {
            if (nonNull(headers)) {
                grpcAuditLog.setMetadata(headers);
            }
            super.onHeaders(headers);
        }

        @Override
        public void onClose(Status status, Metadata trailers) {
            grpcAuditLog.setCode(status.getCode());
            if (nonNull(status.getCode()) && !status.getCode().equals(Status.Code.OK)
                    && nonNull(status.getDescription())) {
                grpcAuditLog.setResponseBody(builderBody(status.getDescription()));
            }
            registerGrpcAuditLog(grpcAuditLog, startTimestamp);
            super.onClose(status, trailers);
        }

        // other options supported by client side interceptor listener that can add more
        // event tracking log infos
        @Override
        public void onReady() {
            super.onReady();
        }
    }
}


