pipeline('frontend-ci') {
 configure { flowdefinition ->
    flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps'){
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git'){
            'userRemoteConfigs' {
                'hudson.plugins.git.UserRemoteConfig' {
                    'url'('https github')
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