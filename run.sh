#!/bin/bash

mvn clean
mvn package -DskipTests

docker compose down
docker compose build --no-cache
docker compose up -d