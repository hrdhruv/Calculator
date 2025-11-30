pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials-id')
        DOCKERHUB_REPO = 'harsh4710/scientific-calculator'
        NOTIFY_EMAIL = 'Harshdhruv889@gmail.com'   
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out repository..."
                git branch: 'main', url: 'https://github.com/hrdhruv/Calculator'
            }
        }

        stage('Build & Test') {
            steps {
                echo "Building and testing application..."
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                script {
                    sh "docker build -t ${DOCKERHUB_REPO}:${BUILD_NUMBER} ."
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                echo "Pushing image to DockerHub..."
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
                echo "Deploying container using Ansible..."
                script {
                    sh "ansible-playbook -i inventory.ini playbook.yml"
                }
            }
        }
    }

    post {
        success {
            echo "Build ${BUILD_NUMBER} completed successfully!"
            mail to: "${NOTIFY_EMAIL}",
                 subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: """Build SUCCESSFUL 

Project: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Status: SUCCESS

Check details at: ${env.BUILD_URL}
"""
        }
        failure {
            echo "Build ${BUILD_NUMBER} failed!"
            mail to: "${NOTIFY_EMAIL}",
                 subject: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: """Build FAILED 

Project: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Status: FAILED

Check console output: ${env.BUILD_URL}console
"""
        }
        unstable {
            echo " Build ${BUILD_NUMBER} is unstable!"
            mail to: "${NOTIFY_EMAIL}",
                 subject: " UNSTABLE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: """Build UNSTABLE 

Project: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Status: UNSTABLE

Review console output: ${env.BUILD_URL}console
"""
        }
    }
}
