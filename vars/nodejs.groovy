def call () {
    pipeline {
        agent {
            node { label 'workstation'}
        }

        stages {

            stage('Build ') {
                steps {
                    sh 'npm install'
                }
            }

            stage('Unit Test ') {
                steps {
                    echo 'Unit Test'
//             sh 'npm test'
                }
            }

            stage('Code Analysis ') {
                steps {
                    sh 'sonar-scanner -Dsonar.host.url=http://52.90.137.237:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=cart'
                }
            }

            stage('Security scan ') {
                steps {
                    echo 'Security scan'
                }
            }

            stage('Publish Artifact ') {
                when {
                    expression {
                        env.TAG_NAME ==~ ".*"
                    }
                }
                steps {
                    echo 'Publish Artifact'
                    sh 'env'
                }
            }

        }

    }


}