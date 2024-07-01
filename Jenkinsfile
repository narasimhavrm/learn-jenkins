pipeline {
    agent { node { label 'workstation' } }
    environment {
//       SSHCRED = credentials('SSH')
      DEMO_URL = "google.com"
    }
//     options {
//       ansiColor('xterm')
//     }

    parameters {
      string(name: 'INPUT_VAR', defaultValue: 'IaminputVAR', description: 'Just input variable')
    }

    stages {
        stage('Hello-01') {
            steps {
                echo 'Hello 001'
//                 sh 'env'
            }
        }
    }
    post {
      always {
        sh 'I am doing post action - inputvar is $INPUT_VAR'
    }
    }
}
