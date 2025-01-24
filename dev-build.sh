#!/bin/bash

services=("discovery-server" "api-gateway")

for service in "${services[@]}"
do
  echo "Building $service..."
  cd $service || exit 1
  mvn clean package -DskipTests || exit  1
  cd ..
done

echo "All Services built successfully"

echo "Starting service with docker compose..."
docker-compose up --build