#!/bin/zsh

docker run -d --name message-web \
    -p 8580:8580 \
    -e SERVER_PORT=8580 \
    -e MESSAGE_SERVICE_HOST=localhost \
    docker.io/nanachi1027/message-web:latest