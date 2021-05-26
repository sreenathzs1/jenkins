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
        stage('Download Dependencies-frontend') {
            when {
                environment name: 'APP_TYPE', value: 'NGINX'
            }
            steps {
                sh '''
                npm install 
                sudo npm install -g npm@latest
                npm run build
                '''
            }
        }

         stage('compile code & Package') {
             when {
                environment name: 'APP_TYPE', value: 'JAVA'
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

        stage('Go Get Git hubs files') {
             when {
                environment name: 'APP_TYPE', value: 'GO_LANG'
            }
            steps {
                sh '''
                go get "github.com/dgrijalva/jwt-go" 
                go get "github.com/labstack/echo" 
                go get "github.com/labstack/echo/middleware" 
                go get "github.com/labstack/gommon/log" 
                go get "github.com/openzipkin/zipkin-go" 
                go get "github.com/openzipkin/zipkin-go/middleware/http"
                go get "github.com/openzipkin/zipkin-go/reporter/http"
                '''
            }
        }
        stage('Again build') {
             when {
                environment name: 'APP_TYPE', value: 'GO_LANG'
            }
            steps {
                sh '''
                 go build main.go user.go tracing.go 
                 go build
                 '''
            }
        }
        


        stage('Download Dependencies-todo') {
            when {
                environment name: 'APP_TYPE', value: 'TODO'
            }
            steps {
                sh '''
                npm install 
                '''
            }
        }
        
        stages('Preapare Artifacts') {
            steps {
                script {
                    prepare = new nexus()
                    prepare.make_artifacts ("${APP_TYPE}", "${COMPONENT}")
                }
                sh '''
                ls
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