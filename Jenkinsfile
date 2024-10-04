pipeline {
    agent any

    environment {
        SPRING_APP_JAR = "my-shop-0.0.1-SNAPSHOT.jar"  // Replace with your JAR name based on pom.xml
        SERVER_USER = "ec2-user"  // The EC2 user (default for Amazon Linux)
        SERVER_HOST = "ec2-your-ec2-public-ip"  // Replace with your EC2 public IP or DNS
        SERVER_APP_PATH = "/home/ec2-user/app"  // Path to deploy the JAR file on EC2
    }
    stages {
        stage('Execute MySQL Install Script') {
            steps {
                sh '''
                    if ! command -v mariadb &> /dev/null
                    then
                        echo "MariaDB is not installed. Installing MariaDB..."
                        chmod +x mysql_install_file.sh
                        ./mysql_install_file.sh
                    else
                        echo "MariaDB is already installed. Skipping installation."
                    fi

                    # Get database server address details
                    DB_HOST=$(hostname -I | awk '{print $1}')
                    DB_PORT=3306  # Default MariaDB port
                    echo "Database server address: $DB_HOST:$DB_PORT"
                '''
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }   

        stage('Deploy to EC2') {
            steps {
                sh "pkill -f ${SPRING_APP_JAR} || true"
                sh "java -jar ${SPRING_APP_JAR} &"
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
// stage('Deploy to EC2') {
//             steps {
//                 // Deploy the JAR to EC2 using SSH
//                 sshagent(['ec2-ssh-credentials']) {  // Replace with your SSH credentials ID in Jenkins
//                     sh """
//                     scp target/${SPRING_APP_JAR} ${SERVER_USER}@${SERVER_HOST}:${SERVER_APP_PATH}/
//                     ssh ${SERVER_USER}@${SERVER_HOST} << EOF
//                         pkill -f ${SPRING_APP_JAR} || true  # Stop existing application if running
//                         nohup java -jar ${SERVER_APP_PATH}/${SPRING_APP_JAR} > /dev/null 2>&1 &  # Start the new version
//                     EOF
//                     """
//                 }
//             }
//         }
// # Explanation:
// # 1. pkill -f ${SPRING_APP_JAR} || true
// #    - This command attempts to stop any existing instance of the application.
// #    - 'pkill' sends a signal to terminate processes.
// #    - '-f' option allows matching against the full command line.
// #    - '${SPRING_APP_JAR}' is the name of the JAR file to match.
// #    - '|| true' ensures the script continues even if no matching process is found.

// # 2. nohup java -jar ${SERVER_APP_PATH}/${SPRING_APP_JAR} > /dev/null 2>&1 &
// #    - This command starts the new version of the application.
// #    - 'nohup' allows the process to continue running even if the SSH session is closed.
// #    - 'java -jar' runs the Java application from the JAR file.
// #    - '${SERVER_APP_PATH}/${SPRING_APP_JAR}' is the full path to the JAR file.
// #    - '> /dev/null 2>&1' redirects both stdout and stderr to /dev/null, suppressing output.
// #    - '&' at the end runs the process in the background.

// # These commands ensure that any existing instance of the application is stopped
// # before starting a new instance, allowing for smooth updates and deployments.  