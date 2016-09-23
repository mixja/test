node {
  checkout scm

  def branch = env.BRANCH_NAME_NOT_EXIST ?: 'master'

  stage('Test') {
    if (branch == 'master') {
      sh 'echo hello world'
    } else {
      sh 'echo on a branch'
    }
  }
}