def nexus() {
    command = "curl -f -v -u admin:admin123 --upload-file /home/ubuntu/workspace/CI-Pipelines/frontend-ci/frontend.zip http://172.31.11.166:8081/repository/frontend/frontend1.zip"
    def execute_state=sh(returnStdout: true, script: command)
}

def make_artifacts(APP_TYPE, COMPONENT) {
    if(APP_TYPE == "NGINX") {
        command = "zip -r ${COMPONENT}.zip node_modules dist"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    } else if(APP_TYPE == "NODEJS") {
    command = "cp target/*.jar ${COMPONENT}.jar && zip -r ${COMPONENT}.zip ${COMPONENT}.jar"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    } else if(APP_TYPE == "GO_LANG") {
        command = "zip -r ${COMPONENT}.zip *"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    } else if(APP_TYPE == "TODO") {
        command = "zip -r ${COMPONENT}.zip node_modules server.js"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    }