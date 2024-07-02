def call () {
    node('workstation') {

        if (env.cibuild == "nodejs") {
            stage('Build ') {
                sh 'npm install'
            }
        }
        if (env.cibuild == "java") {
            stage('Build ') {
                sh 'mvn package'
            }
        }
        stage('Unit Test ') {
            echo 'Unit Test'
//             sh 'npm test'
        }

        stage('Code Analysis ') {
            echo 'Sonar '
//            sh 'sonar-scanner -Dsonar.host.url=http://52.90.137.237:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=cart'
        }

        stage('Security scan ') {
            echo 'Security scan'
        }


            if(env.TAG_NAME ==~ ".*") {
                stage('Publish Artifact ') {
                    echo 'Publish Artifact'
//                    sh 'env'

                }
            }

    }
}