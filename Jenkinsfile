pipeline {
    agent { node { label 'workstation' } }
    environment {
            SSH_CREDS = credentials('SSH')
        }

    parameters {
            string(name: 'INPUT_VAR', defaultValue: 'IaminputVAR', description: 'Just input variable')
    }

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
                sh 'SSH_CREDS'
            }
        }
    }
    post {
      always {
        echo 'I am doing post action - inputvar is $INPUT_VAR'
        echo 'SSH Creds are $SSH_CREDS'

    }
    }
}
