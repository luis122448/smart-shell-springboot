#!/bin/bash

# Create environment variables file
touch .env

# Load the environment variables from the .env file
# Note: use "set -a" to export variables automatically
set -a
source .env
set +a

# Application properties
APPLICATION_PDN="./src/main/resources/application-pdn.properties"
APPLICATION_DEV="./src/main/resources/application-dev.properties"

# Copy the content of the application-dev.properties file to the application-pdn.properties file
cp $APPLICATION_PDN $APPLICATION_DEV

sed -i "s|\${POSTGRES_HOST}|$SMART_SHELL_POSTGRES_HOST|g" "$APPLICATION_DEV"
sed -i "s|\${POSTGRES_PORT}|$SMART_SHELL_POSTGRES_PORT|g" "$APPLICATION_DEV"
sed -i "s|\${POSTGRES_DATABASE}|$SMART_SHELL_POSTGRES_DATABASE|g" "$APPLICATION_DEV"
sed -i "s|\${POSTGRES_USERNAME}|$SMART_SHELL_POSTGRES_USERNAME|g" "$APPLICATION_DEV"
sed -i "s|\${POSTGRES_PASSWORD}|$SMART_SHELL_POSTGRES_PASSWORD|g" "$APPLICATION_DEV"
sed -i "s|\${MONGO_HOST}|$SMART_SHELL_MONGO_HOST|g" "$APPLICATION_DEV"
sed -i "s|\${MONGO_PORT}|$SMART_SHELL_MONGO_PORT|g" "$APPLICATION_DEV"
sed -i "s|\${MONGO_DATABASE}|$SMART_SHELL_MONGO_DATABASE|g" "$APPLICATION_DEV"
sed -i "s|\${MONGO_USERNAME}|$SMART_SHELL_MONGO_USERNAME|g" "$APPLICATION_DEV"
sed -i "s|\${MONGO_PASSWORD}|$SMART_SHELL_MONGO_PASSWORD|g" "$APPLICATION_DEV"
sed -i "s|\${REDIS_HOST}|$SMART_SHELL_REDIS_HOST|g" "$APPLICATION_DEV"
sed -i "s|\${REDIS_PORT}|$SMART_SHELL_REDIS_PORT|g" "$APPLICATION_DEV"
sed -i "s|\${REDIS_USERNAME}|$SMART_SHELL_REDIS_USERNAME|g" "$APPLICATION_DEV"
sed -i "s|\${REDIS_PASSWORD}|$SMART_SHELL_REDIS_PASSWORD|g" "$APPLICATION_DEV"

# Install java
sudo apt install openjdk-17-jdk
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# Build the application
sudo apt update
sudo apt install maven

mvn clean install