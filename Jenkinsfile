node {
  checkout scm

  stage('Test') {
    if (env.BRANCH_NAME == 'master') {
      sh 'echo hello world'
    } else {
      sh 'echo on a branch'
    }
  }
}