pipeline {
    agent any

    environment {
        PROJECT_NAME = "ROBOSHOP"
    }
    
    stages {

        stage('ONE') {
            steps{
                sh "echo ${PROJECT_NAME}"
            }
        }
        stage('two') {

            environment {
                PROJECT_NAME = "TODO"
            }
            steps {
                sh "echo ${PROJECT_NAME}"
            }
        }
    }

}