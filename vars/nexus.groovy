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
}


def code_build(APP_TYPE, COMPONENT) {
    if(APP_TYPE == "NGINX") {
        command = "npm install && sudo npm install -g npm@latest && npm run build"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    } else if(APP_TYPE == "NODEJS") {
    command = "mvn clean package"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    } else if(APP_TYPE == "GO_LANG") {
        command = "go build && go get github.com/dgrijalva/jwt-go && go get github.com/labstack/echo && go get github.com/labstack/echo/middleware && go get github.com/labstack/gommon/log && go get github.com/openzipkin/zipkin-go && go get github.com/openzipkin/zipkin-go/middleware/http && go get github.com/openzipkin/zipkin-go/reporter/http && go build main.go user.go tracing.go && go build"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    } else if(APP_TYPE == "TODO") {
        command = "npm install"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    }
}