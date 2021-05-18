folder('CI-Pipelines') {
    displayName('CI Pipelines')
    description('CI Pipelines')
}

pipelineJob('CI-Pipelines/frontend-ci') {
 configure { flowdefinition ->
    flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps'){
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git'){
            'userRemoteConfigs' {
                'hudson.plugins.git.UserRemoteConfig' {
                    'url'('https://github.com/sreenathzs1/frontend.git')
                }
            }
            'branches' {
                'hudson.plugins.git.BranchSpec' {
                    'name'('*/main')
                }
            }
        }
        'scriptpath'('jenkinsfile')
        'lightweight'(true)
    }
 }
}

pipelineJob('CI-Pipelines/users-ci') {
 configure { flowdefinition ->
    flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps'){
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git'){
            'userRemoteConfigs' {
                'hudson.plugins.git.UserRemoteConfig' {
                    'url'('https://github.com/sreenathzs1/users.git')
                }
            }
            'branches' {
                'hudson.plugins.git.BranchSpec' {
                    'name'('*/main')
                }
            }
        }
        'scriptpath'('Jenkinsfile')
        'lightweight'(true)
    }
 }
}

pipelineJob('CI-Pipelines/login-ci') {
 configure { flowdefinition ->
    flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps'){
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git'){
            'userRemoteConfigs' {
                'hudson.plugins.git.UserRemoteConfig' {
                    'url'('https://github.com/sreenathzs1/login.git')
                }
            }
            'branches' {
                'hudson.plugins.git.BranchSpec' {
                    'name'('*/main')
                }
            }
        }
        'scriptpath'('Jenkinsfile')
        'lightweight'(true)
    }
 }
}

pipelineJob('CI-Pipelines/todo-ci') {
 configure { flowdefinition ->
    flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps'){
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git'){
            'userRemoteConfigs' {
                'hudson.plugins.git.UserRemoteConfig' {
                    'url'('https://github.com/sreenathzs1/todo.git')
                }
            }
            'branches' {
                'hudson.plugins.git.BranchSpec' {
                    'name'('*/main')
                }
            }
        }
        'scriptpath'('Jenkinsfile')
        'lightweight'(true)
    }
 }
}

pipelineJob('CI-Pipelines/redis-ci') {
 configure { flowdefinition ->
    flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps'){
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git'){
            'userRemoteConfigs' {
                'hudson.plugins.git.UserRemoteConfig' {
                    'url'('https://github.com/sreenathzs1/redis.git')
                }
            }
            'branches' {
                'hudson.plugins.git.BranchSpec' {
                    'name'('*/main')
                }
            }
        }
        'scriptpath'('Jenkinsfile')
        'lightweight'(true)
    }
 }
}