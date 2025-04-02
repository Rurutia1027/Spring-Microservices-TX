#!/bin/zsh

docker run -d --name message-rpc-server \
  -p 8581:8581 \
  -p 59081:59081 \
  -e SPRING_APPLICATION_NAME=grpc-server \
  -e SPRING_LIFECYCLE_TIMEOUT_PER_SHUTDOWN_PHASE=2m \
  -e SPRING_MAIN_ALLOW_BEAN_DEFINITION_OVERRIDING=true \
  -e SERVER_PORT=8581 \
  -e SERVER_HTTP2_ENABLED=true \
  -e GRPC_SERVER_ADDRESS=localhost \
  -e GRPC_SERVER_PORT=59081 \
  -e MANAGEMENT_HEALTH_DISKSPACE_ENABLED=false \
  -e MANAGEMENT_TRACING_ENABLED=true \
  -e MANAGEMENT_TRACING_SAMPLING_PROBABILITY=1.0 \
  -e MANAGEMENT_ENDPOINTS_ENABLED_BY_DEFAULT=false \
  -e MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE=health \
  -e MANAGEMENT_ENDPOINT_INFO_ENABLED=true \
  -e MANAGEMENT_ENDPOINT_HEALTH_ENABLED=true \
  -e MANAGEMENT_ENDPOINT_HEALTH_SHOW_DETAILS=always \
  -e LOGGING_PATTERN_CONSOLE="{ \"datetime\": \"%d{yyyy-MM-dd HH:mm:ss.SSS}\", \"level\": \"%level\", \"traceId\": \"%mdc{traceId}\", \"spanId\": \"%mdc{spanId}\", \"thread\": \"%thread\", \"class\": \"%c{2.}\", \"message\": \"%m\"}%n" \
  nanachi1027/message-rpc-server:latest