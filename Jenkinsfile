pipeline {
  agent {
    node {
      label 'eco-agent'
    }

  }
  stages {
    stage('startBuild') {
      steps {
        sh '''#!/bin/sh
chmod a+x ./gradlew
./gradlew clean build'''
      }
    }

  }
}