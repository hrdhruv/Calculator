pipeline {
    agent any

    environment {
        // Using your kubeconfig for Kubernetes access
        KUBECONFIG = "/home/harsh-d/.kube/config"
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
                echo "Building and testing the application..."
                sh 'mvn clean package'
            }
        }

        stage('DevSecOps Scan (Trivy)') {
            steps {
                echo "Running security scan using Trivy..."
                sh """
                trivy fs --exit-code 0 --severity HIGH,CRITICAL .
                """
            }
        }

        stage('Kubernetes Access Test') {
            steps {
                echo "Testing Kubernetes access..."

                sh "kubectl config get-contexts"
                sh "kubectl get nodes"
                sh "kubectl get pods -A"
            }
        }

        // ❌ All other steps commented out as requested
        /*
        stage('Build Docker Image') { ... }
        stage('Push to DockerHub') { ... }
        stage('Deploy with Ansible') { ... }
        stage('K8s Deploy') { ... }
        */

    }

    post {
        success {
            echo "Pipeline completed successfully!"
            mail to: "${NOTIFY_EMAIL}",
                 subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: """Build SUCCESSFUL 

Project: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}
Status: SUCCESS

K8s connection and DevSecOps scan successful.
"""
        }

        failure {
            echo "Pipeline failed!"
            mail to: "${NOTIFY_EMAIL}",
                 subject: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: """Build FAILED

Check Jenkins console output for details.
"""
        }
    }
}
