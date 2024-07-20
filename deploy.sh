#!/bin/bash
# Description: Deploy the application

# Stop the application
sudo docker compose down

# Pull the latest changes
sudo git pull origin main

# Create directory
mkdir -p app/

# Delete content
rm -rf app/*

# Copy archive
cp ./target/smart-shell-1.0.0.jar ./app/smart-shell.jar

#
chmod +x ./dev-install.sh

# Install scrip
sudo bash ./dev-install.sh

# Build and run the application
sudo docker compose up --build --force-recreate --no-deps -d
