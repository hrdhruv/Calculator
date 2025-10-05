pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials-id')
        DOCKERHUB_REPO = 'harsh4710/scientific-calculator'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "📦 Checking out repository..."
                git branch: 'main', url: 'https://github.com/harsh4710/MINI_PROJECT.git'
            }
        }

        stage('Build & Test') {
            steps {
                echo "🧪 Building and testing application..."
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "🐳 Building Docker image..."
                script {
                    sh "docker build -t ${DOCKERHUB_REPO}:${BUILD_NUMBER} ."
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                echo "📤 Pushing image to DockerHub..."
                script {
                    sh "echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
                    sh "docker push ${DOCKERHUB_REPO}:${BUILD_NUMBER}"
                    sh "docker tag ${DOCKERHUB_REPO}:${BUILD_NUMBER} ${DOCKERHUB_REPO}:latest"
                    sh "docker push ${DOCKERHUB_REPO}:latest"
                }
            }
        }

        stage('Deploy with Ansible') {
            steps {
                echo "🚀 Deploying container using Ansible..."
                script {
                    sh '''
                        ansible-playbook -i ansible/inventory.ini ansible/playbook.yml
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Build ${BUILD_NUMBER} completed successfully!"
        }
        failure {
            echo " Build ${BUILD_NUMBER} failed!"
        }
    }
}
