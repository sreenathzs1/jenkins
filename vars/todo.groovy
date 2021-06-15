def call(Map Params =[:]) {
 // Start Default Arguments
 def args = [
        NEXUS_IP           : '172.31.7.112',
 ]
 args << Params
 // End Default + Requried argumanets
pipeline {
    agent {
        label "${args.SLAVE_LABEL}"
    }

    triggers {
        pollSCM('*/2 * * * 1-5')
    }



    environment {
        COMPONENT       = "${args.COMPONENT}"
        NEXUS_IP        = "${args.NEXUS_IP}"
        PROJECT_NAME    = "${args.PROJECT_NAME}"
        SLAVE_LABEL     = "${args.SLAVE_NAME}"
        APP_TYPE        = "${args.APP_TYPE}"
    }
    stages {
        stage('Build Code & Install Dependencies') {
            steps {
            script {
                    build = new nexus()
                    build.code_build("${APP_TYPE}", "${COMPONENT}")
                }
                
            }
        }
           
          
        stage('Prepare Artifacts') {
            steps {
                script {
                    prepare = new nexus()
                    prepare.make_artifacts("${APP_TYPE}", "${COMPONENT}")
                }
            }
        }
        stage('Upload Artifacts') {
            steps {
                script {
                    prepare = new nexus()
                    prepare.nexus(COMPONENT)
                }        
           }

        }

        stage('Deploy to Dev Env') {
            
        }
    }
}
}