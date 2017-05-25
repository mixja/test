def projectConfigFile = readFileFromWorkspace('config/projects.groovy')
def projectConfig = new ConfigSlurper().parse(projectConfigFile)

projectConfig.projects.each { project -> 
    job(project.name + '-seed-job') {
        def jobPath = project.JobPath ?: "job.groovy"
        if (project.disabled) {
            disabled()
        }

        logRotator {
            daysToKeep(90)
        }

        scm {
            git {
                remote {
                    url(project.url)
                }
            }
        }

        triggers {
            scm 'H/5 * * * *'
        }

        steps {
            dsl {
                external jobPath
            }
            environmentVariables {
                env('GIT_URL',project.url)
            }
        }
    }
}