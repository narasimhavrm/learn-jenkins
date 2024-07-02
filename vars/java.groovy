def call () {
    pipeline {
        agent {
            node { label 'workstation'}
        }

        stages {

            stage('Build ') {
                steps {
                    sh 'mvn package'
                }
            }

            stage('Unit Test ') {
                steps {
                    echo 'Unit Test'
//             sh 'mvn test'
                }
            }

            stage('Code Analysis ') {
                steps {
                    sh 'sonar-scanner -Dsonar.host.url=http://52.90.137.237:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=shipping -Dsonar.java.binaries=target'
                }
            }

            stage('Security scan ') {
                steps {
                    echo 'Security scan'
                }
            }

            stage('Publish Artifact ') {
                steps {
                    echo 'Publish Artifact'
                }
            }

        }

    }
}