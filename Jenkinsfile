pipeline {
  agent any
  stages {
    stage('startBuild') {
      steps {
        dockerNode(image: 'openjdk:17') {
          sh '''#!/bin/sh
chmod a+x ./gradlew
./gradlew clean build'''
        }

      }
    }

  }
}