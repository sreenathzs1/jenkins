def call(Map Params =[:]) {
 // Start Default Arguments
 def args = [
        NEXUS           : 'some',
 ]
 args << Params
 // End Default + Requried argumanets
pipeline {
    agent {
        label 'NODEJS'
    }
    environment {
        COMPONENT = "${args.COMPONENT}"
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
                 echo ${COMPONENT}
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