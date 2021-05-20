def call() {

pipeline {
    agent {
        label 'NODEJS'
    }

    stages {
        stage('Download Dependencies') {
            steps {
                sh '''
                npm install 
                npm run build
                '''
            }
        }



        stage('preapare Artifact') {
            steps {
                sh '''
                 zip -r frontend.zip node_modules dist
                 '''
            }
        }

        stage('Upload Artifacts') {
            steps {
                script {
                    nexus
                }        
           }

        }
    }
}
}