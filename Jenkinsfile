pipeline {
    agent any

    environment {
        MYSQL_ROOT_PASSWORD = 'root'
        MYSQL_PORT = '3306'
        SCRIPT_PATH = 'script.sql'  // Chemin vers ton script SQL
        NETWORK_NAME = 'my_network' // Nom du réseau Docker
    }

    stages {
        stage('Stop and Remove Existing Containers') {
            steps {
                script {
                    // Arrêter et supprimer les conteneurs existants
                    sh 'docker rm -f mysql'
                    sh 'docker rm -f tpapi'
                }
            }
        }

        stage('Setup Docker Network') {
            steps {
                script {
                    // Vérifie si le réseau existe, sinon le crée
                    sh 'docker network inspect ${NETWORK_NAME} || docker network create ${NETWORK_NAME}'
                }
            }
        }
        stage('Build MySQL Image') {
            steps {
                script {
                    sh """
                        echo 'FROM mysql:8.0' > Dockerfile
                        echo 'COPY ${SCRIPT_PATH} /docker-entrypoint-initdb.d/' >> Dockerfile
                        docker build -t mysql-with-script .
                    """
                }
            }
        }

        stage('Run MySQL Container') {
            steps {
                script {
                    // Lancer MySQL dans le réseau `my_network`
                    sh """
                        docker run --name mysql --network ${NETWORK_NAME} \\
                        -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} \\
                        -p 3306:3306 -d mysql-with-script
                    """
                }
            }
        }

        stage('Wait for MySQL') {
            steps {
                script {
                    // Attendre que MySQL soit prêt
                    sh '''
                    echo "Waiting for MySQL to be ready..."
                    while ! docker exec mysql mysqladmin --user=root --password=${MYSQL_ROOT_PASSWORD} --host=mysql --silent ping; do
                        sleep 10
                    done
                    echo "MySQL is ready!"
                    '''
                }
            }
        }


        stage('Pull TPAPI Image') {
            steps {
                script {
                    sh 'docker pull mathaus26/tpapi:latest'
                }
            }
        }

        stage('Run TPAPI Application') {
            steps {
                script {
                    // Lancer l'application dans le même réseau et lui dire que MySQL est accessible via "mysql:3306"
                    sh """
                        docker run --name tpapi -d --network ${NETWORK_NAME} \\
                        -p 9090:9000 -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/springboot \\
                        mathaus26/tpapi:latest
                    """
                }
            }
        }
        
        
    }

}