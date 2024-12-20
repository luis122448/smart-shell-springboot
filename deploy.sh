#!/bin/bash 
# Description: Deploy the application
set -e
set -x

# Stop the application
sudo docker compose down

# Pull the latest changes
git pull origin main

# Build and run the application
sudo docker compose up --build --force-recreate --no-deps -d