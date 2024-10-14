#!/bin/bash

./AccelByte.Extend.Vivox.Authentication.Server &

./grpc_gateway &

wait -n

exit $?

# https://docs.docker.com/config/containers/multi-service_container/#use-a-wrapper-script