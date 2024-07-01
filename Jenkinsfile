pipeline {
    agent { node { label 'workstation' } }
    environment {
      SSHCRED = credentials('SSH')
      DEMO_URL = "google.com"
    }
    options {
      ansiColor('xterm')
    }

    triggers { pollSCM('H/2 * * * *') }

    parameters {
      string(name: 'INPUT_VAR', defaultValue: 'IaminputVAR', description: 'Just input variable')
    }

    stages {
        stage('Hello-01') {
            input {
              message "Should we continue?"
              ok "Yes, we should."
            }
            steps {
                echo 'Hello 001'
                sh 'echo input var is $INPUT_VAR'
                sh 'env'
            }
        }
        stage('Example Deploy') {
                    when {
                        branch 'production'
                    }
        }

    }
    post {
      always {
        sh 'echo Post action $INPUT_VAR'
    }
    }
}

//comment
