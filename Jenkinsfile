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
                sh 'echo input var is $INPUT_VAR'
//                 sh 'env'
            }
        }
    }
    post {
      always {
        sh 'echo Post action $INPUT_VAR'
    }
    }
}
