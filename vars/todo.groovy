def call(Map Params =[:]) {
 // Start Default Arguments
 def args = [
        NEXUS_IP           : '172.31.11.166',
 ]
 args << Params
 // End Default + Requried argumanets
pipeline {
    agent {
        label "${args.SLAVE_LABEL}"
    }
    environment {
        COMPONENT       = "${args.COMPONENT}"
        NEXUS_IP        = "${args.NEXUS_IP}"
        PROJECT_NAME    = "${args.PROJECT_NAME}"
        SLAVE_LABEL     = "${args.SLAVE_NAME}"
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