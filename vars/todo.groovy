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
        APP_TYPE        = "${args.APP_TYPE}"
    }
    stages {
        stage('Download Dependencies') {
            when {
                environement name: 'APP_TYPE', value: 'NGINX'
            }
            steps {
                sh '''
                npm install 
                sudo npm install -g npm@latest
                npm run build
                '''
            }
        }

        stage('preapare Artifact') {
            when {
                environment name: 'COMPONENT', value: 'frontend'
            }
            steps {
                sh '''
                 zip -r ${COMPONENT}.zip node_modules dist
                 '''
            }
        }

         stage('compile code & Package') {
             when {
                environement name: 'APP_TYPE', value: 'JAVA'
            }
            steps {
             sh '''
             mvn clean package
             '''
            }  
        }
        //stage('make package') {
         //steps {
            // sh '''
            // mvn package
             //'''
            //}  

       // }

        stage('preapare Artifact') {
            when {
                environement name: 'APP_TYPE', value: 'JAVA'
            }
            steps {
                sh '''
                 cp target/*.jar users.jar
                 zip -r users.zip users.jar
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