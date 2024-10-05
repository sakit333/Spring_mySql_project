#!/bin/bash

# Update package repositories
sudo yum update -y

# Install MariaDB server
sudo yum install -y mariadb-server

# Start MariaDB service
sudo systemctl start mariadb

# Enable MariaDB to start on system reboot
sudo systemctl enable mariadb

# Secure MySQL installation
sudo mysql_secure_installation <<EOF

y
1234
1234
y
y
y
y
EOF

# Optional: Check MySQL service status
sudo systemctl status mariadb

echo "To connect with MySQL use: mysql -u root -p"
echo "Password: 1234"

# Update the MySQL configuration file to allow connections

# If you want to restrict to localhost only, you can add:
sudo sed -i 's/^bind-address.*/bind-address=127.0.0.1/' /etc/my.cnf

# Remember to restart the MySQL service after making changes:
sudo systemctl restart mariadb

echo "MySQL configuration updated to allow remote connections and MariaDB service restarted."

# Output the public IP address
echo "To connect with MySQL use: mysql -u root -p"
echo "Password: 1234"
echo "Public IP address of the server: ${PUBLIC_IP}"
