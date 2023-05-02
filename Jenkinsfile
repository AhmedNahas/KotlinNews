pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building....'
      }
    }

    stage('Test') {
      steps {
        echo 'Testing'
      }
    }

    stage('Deploy') {
      steps {
        input(message: 'Deploy?', ok: 'Yes')
        echo 'Deploying...'
      }
    }

  }
}