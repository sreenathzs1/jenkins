folder('CI-Pipelines') {
    displayName('CI Pipelines')
    description('CI Pipelines')
}

def component = ["frontend","users","login","todo"];

def count=(component.size()-1)
for (i in 0..count) {
    def j=component[i]
pipelineJob("CI-Pipelines/${j}-ci") {
 configure { flowdefinition ->
    flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
        'triggers' {
            'hudson.triggers.SCMTrigger' {
                'spec'('*/2 * * * 1-5')
                'ignorePostCommitHooks'(false)
            }
        }
    }
    flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps'){
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git'){
            'userRemoteConfigs' {
                'hudson.plugins.git.UserRemoteConfig' {
                    'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
                    'url'('https://github.com/sreenathzs1/'+j+'.git')
                }
            }
            'branches' {
                'hudson.plugins.git.BranchSpec' {
                    'name'('*/tags/*')
                }
            }
        }
        'scriptpath'('Jenkinsfile')
        'lightweight'(true)
        }
    }
}   
}


pipelineJob("Deployment Pipeline") {
 configure { flowdefinition ->
    flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps'){
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git'){
            'userRemoteConfigs' {
                'hudson.plugins.git.UserRemoteConfig' {
                    'url'('https://github.com/sreenathzs1/jenkins.git')
                }
            }
            'branches' {
                'hudson.plugins.git.BranchSpec' {
                    'name'('main')
                }
            }
        }
        'scriptpath'('Jenkinsfile-Deployment')
        'lightweight'(true)
        }
    }
}

