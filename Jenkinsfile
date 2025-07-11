pipeline {
    agent any

    tools {
        jdk 'Java 17'
        maven 'Maven 3.9.4'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-user/your-springboot-repo.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package Docker') {
            steps {
                sh 'docker build -t springboot-demo:latest .'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                    docker stop springboot-app || true
                    docker rm springboot-app || true
                    docker run -d -p 8080:8080 --name springboot-app springboot-demo:latest
                '''
            }
        }
    }

    post {
        success {
            echo 'Build and Deployment Succeeded'
        }
        failure {
            echo 'Build Failed'
        }
    }
}