#!/bin/zsh

docker run -d --name message-service \
    -p 59081:59081 \  # Maps the internal message-service rpc client port to the host
    -p 8580:8580 \    # Maps the web application port
    -e SERVER_PORT=8580 \  # Sets the server ports as an environment variable
    -e MESSAGE_SERVICE_HOST=localhost \  # Sets the service host
    -e MESSAGE_SERVICE_PORT=59081 \      # Sets service port
    docker.io/nanachi1027/message-service:latest