# Description: Deploy the application

# Create directory
mkdir -p app/

# Copy archive
cp ./target/smart-shell-1.0.0.jar ./app/smart-shell.jar

# Stop the application
sudo docker-compose down

# Pull the latest changes
sudo git pull origin main

# Build and run the application
sudo docker-compose up --build --force-recreate --no-deps -d