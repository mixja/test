import jenkins.automation.utils.ScmUtils
import jenkins.automation.utils.EnvironmentUtils

def projectConfigFile = readFileFromWorkspace('config/projects.groovy')

def projectConfig = new ConfigSlurper().parse(projectConfigFile)

projectConfig.projects.each { project -> 
    job(project.name + '-seed-job') {
        if (project.disabled) {
            disabled()
        }
        logRotator {
            daysToKeep(90)
        }

        triggers {
            scm 'H/5 * * * *'
        }

        steps {
            dsl {
                external = project.jobPath ?: "job.groovy"
            }

            shell('echo hello world')
        }
    }
}