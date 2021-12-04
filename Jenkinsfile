pipeline {
  agent any
  stages {
    stage('Build') {
      agent {
        docker {
          reuseNode true
          image 'gradle:7.3.1-jdk17'
        }

      }
      steps {
        sh 'gradle clean build'
      }
    }

  }
}