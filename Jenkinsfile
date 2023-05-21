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
      parallel {
        stage('Deploy') {
          steps {
            input(message: 'Deploy?', ok: 'Yes')
            echo 'Deploying...'
          }
        }

        stage('Test env') {
          steps {
            echo 'holaaaaaaaaa'
          }
        }

      }
    }

  }
}