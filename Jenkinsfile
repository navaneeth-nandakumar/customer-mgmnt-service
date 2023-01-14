pipeline {
    agent any
    tools{
        maven 'maven_3_8_7'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/navaneeth-nandakumar/customer-mgmnt-service']]])
                sh 'mvn clean install -Dmaven.test.skip=true'
            }
        }

        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t completese/customer-mgmnt-svc .'
                }
            }
        }

        stage('Push image to Hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                      sh 'docker login -u completese -p ${dockerhubpwd}'
                    }
                    sh 'docker push completese/customer-mgmnt-svc'
                }
            }
        }
    }
}