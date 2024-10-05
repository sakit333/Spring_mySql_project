pipeline {
    agent any

    tools {
        maven 'maven'
    }
    environment {
        SPRING_APP_JAR = "my-shop-1.0.jar"  // Replace with your JAR name based on pom.xml
        // SERVER_USER = "root"  // The EC2 user (default for Amazon Linux)
        // SERVER_HOST = "ec2-your-ec2-public-ip"  // Replace with your EC2 public IP or DNS
        // SERVER_APP_PATH = "/home/ec2-user/app"  // Path to deploy the JAR file on EC2
    }
    stages {
        stage('Execute MySQL Install Script') {
            steps {
                script {
                    // Check if MariaDB server is installed
                    def isInstalled = sh(script: "rpm -qa | grep -qw mariadb-server", returnStatus: true) == 0
                    if (!isInstalled) {
                        echo "MariaDB is not installed. Installing MariaDB..."
                        sh "chmod +x mysql_install_file.sh"   // Ensure the MySQL install script is executable
                        sh "sudo ./mysql_install_file.sh"   // Run the MySQL installation script as root
                        isInstalled = sh(script: "rpm -qa | grep -qw mariadb-server", returnStatus: true) == 0  // Verify if installation was successful
                        if (isInstalled) {
                            echo "MariaDB installation successful."
                        } else {
                            echo "MariaDB installation failed."
                            error("Exiting the pipeline due to MariaDB installation failure.")
                        }
                        } else {
                            echo "MariaDB is already installed. Skipping installation."
                        }
                        
                        // Get database server address details
                        def dbHost = sh(script: 'hostname -I | awk "{print $1}"', returnStdout: true).trim()
                        def dbPort = 3306  // Default MariaDB port
                        echo "Database server address: ${dbHost}:${dbPort}"
                    }
                }
            }

        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }   

        stage('Deploy to EC2') {
            steps {
                 script {
                    // Check if the Spring application is running
                    def isRunning = sh(script: "pgrep -f target/${SPRING_APP_JAR}", returnStatus: true) == 0
                    // If running, stop it
                    if (isRunning) {
                        echo "Stopping the running Spring application..."
                        sh "sudo pkill -f target/${SPRING_APP_JAR}"
                    } else {
                        echo "No running instance found."
                    }
                    // Start the Spring application
                    echo "Starting the Spring application..."
                    sh "sudo nohup java -jar target/${SPRING_APP_JAR} > /dev/null 2>&1 &"
                }
            }
        }
    }

    post {
        success {
            echo 'Application deployed successfully!'
        }
        failure {
            echo 'Application deployment failed.'
        }
    }
}
