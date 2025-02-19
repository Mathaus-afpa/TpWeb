pipeline {
    agent any

    environment {
        MYSQL_ROOT_PASSWORD = 'root'
		DOCKER_COMPOSE_PATH = './docker-compose.yml' 
    }

    stages {
	
        stage('Suppression des containers') {
            steps {
                script {
                    // Arrêter et supprimer les conteneurs existants
                    sh 'docker rm -f mysql'
                    sh 'docker rm -f tpapi'
					sh 'docker rm -f tpweb'
                }
            }
        }
		
		stage('Clean') {
            steps {
                cleanWs()
            }
        }
		

		stage('Checkout code') {
            steps {
                checkout scm
            }
        }
        
        stage('Docker compose') {
            steps {
                script {
                    sh 'docker-compose -f ${DOCKER_COMPOSE_PATH} up -d'  // Démarre les services en arrière-plan avec docker-compose
                }
            }
        }   
        
    }

}