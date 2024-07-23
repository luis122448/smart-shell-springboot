# Description: Deploy the application

# Execute the script from the root directory of the project
#sudo bash `./dev-install.sh`

# Stop the application
sudo docker compose down

# Pull the latest changes
#sudo git pull origin main

# Build and run the application
sudo docker compose up --build --force-recreate --no-deps -d
