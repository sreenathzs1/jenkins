def nexus(COMPONENT) {
    get_branch = "env | grep GIT_BRANCH | awk -F / '{print \$NF}' | xargs echo -n"
    def get_branch_exec=sh(returnStdout: true, script: get_branch)
    def FILENAME=COMPONENT+'-'+get_branch_exec+'.zip'
    command = "curl -f -v -u admin:admin123 --upload-file ${FILENAME} http://172.31.7.112:8081/repository/${COMPONENT}/${FILENAME}"
    def execute_state=sh(returnStdout: true, script: command)
}

def make_artifacts(APP_TYPE, COMPONENT) {
    get_branch = "env | grep GIT_BRANCH | awk -F / '{print \$NF}' | xargs echo -n"
    def get_branch_exec=sh(returnStdout: true, script: get_branch)
    def FILENAME=COMPONENT+'-'+get_branch_exec+'.zip'
    if(APP_TYPE == "NGINX") {
        command = "zip -r ${FILENAME} *"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    } else if(APP_TYPE == "JAVA") {
        command = "cp target/*.jar ${COMPONENT}.jar && zip -r ${FILENAME} ${COMPONENT}.jar"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    } else if(APP_TYPE == "GO_LANG") {
        command = "zip -r ${FILENAME} *"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    } else if(APP_TYPE == "TODO") {
        command = "zip -r ${FILENAME} *"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    }
}


def code_build(APP_TYPE, COMPONENT) {
    if(APP_TYPE == "NGINX") {
        command = "npm install && sudo npm install -g npm@latest && npm run build"
        command = "env"
        def execute_com=sh(returnStdout:true, script: command)
        print execute_com
    } else if(APP_TYPE == "JAVA") {
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