package com.cloud.payment.audit;

import io.grpc.Status;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Objects;

public class GRpcAuditLogHelper {
    private static final Logger LOG = LoggerFactory.getLogger(GRpcAuditLogHelper.class);

    public static final String TRACE_ID = "traceId";
    public static final String SPAN_ID = "spanId";
    public static final String CALLED_BY = "calledBy";

    public static String builderBody(final String stringBody) {
        if (StringUtils.isNotBlank(stringBody)) {
            return "( " + stringBody.replaceAll("\\r?\\n", "; ") + " )";
        }
        return null;
    }

    public static void registerGrpcAuditLog(final GRpcAuditLog grpcAuditLog,
                                            final Instant startTimestamp) {
        grpcAuditLog.setDuration(Instant.now().toEpochMilli() - startTimestamp.toEpochMilli());
        if (Objects.nonNull(grpcAuditLog.getCode()) && grpcAuditLog.getCode().equals(Status.Code.OK)) {
            LOG.info("GRpc Audit Log {}", grpcAuditLog.toString());
            return;
        }
        LOG.error("GRpc Audit Log {}", grpcAuditLog.toString());
    }
}
