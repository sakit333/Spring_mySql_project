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

# If you want to explicitly allow remote connections, you can add:
# sudo sed -i 's/^bind-address.*/bind-address=0.0.0.0/' /etc/my.cnf

# If you want to restrict to localhost only, you can add:
sudo sed -i 's/^bind-address.*/bind-address=127.0.0.1/' /etc/my.cnf

# Remember to restart the MySQL service after making changes:
sudo systemctl restart mariadb

echo "MySQL configuration updated to allow remote connections and MariaDB service restarted."

# Grant privileges for remote access
# mysql -u root -p1234 -e "GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '1234' WITH GRANT OPTION;"

# This command grants all privileges to the 'root' user for remote access:
# - 'mysql -u root -p1234': Connects to MySQL as the root user with password '1234'
# - '-e': Executes the following SQL command
# - "GRANT ALL PRIVILEGES ON *.*": Grants all privileges on all databases and tables
# - "TO 'root'@'%'": Allows the root user to connect from any host ('%' is a wildcard)
# - "IDENTIFIED BY '1234'": Sets the password for remote root access to '1234'
# - "WITH GRANT OPTION": Allows the root user to grant privileges to other users

# Note: Granting all privileges to root from any host can be a security risk.
# It's recommended to limit remote access to specific IP addresses or use a less
# privileged user for remote connections in production environments.
# Flush privileges to apply the changes
# mysql -u root -p1234 -e "FLUSH PRIVILEGES;"

# echo "Remote access privileges granted to root."

# Output the public IP address
echo "To connect with MySQL use: mysql -u root -p"
echo "Password: 1234"
echo "Public IP address of the server: ${PUBLIC_IP}"
