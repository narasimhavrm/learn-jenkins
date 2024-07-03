def call () {
    node('workstation') {

        stage('Code checkout' ) {
            sh 'find . | grep "^./" | xargs rm -rf '
            if(env.TAG_NAME ==~ ".*"){
                env.gitbrname = "refs/tags/{env.TAG_NAME}"
            } else {
                env.gitbrname = "${env.BRANCH_NAME}"
            }
            checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[url: "https://github.com/narasimhavrm/env.{component}" ]], branches: [[name: gitbrname]], poll: false]
        }

        if (env.cibuild == "nodejs") {
            stage('Build ') {
                sh 'echo npm install'
//                sh 'npm install'
            }
        }
        if (env.cibuild == "java") {
            stage('Build ') {
                 sh 'echo mvn package'
//                sh 'mvn package'
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
                    sh 'rm -rf Jenkinsfile'
                    sh 'echo ${TAG_NAME} > VERSION'
                    sh 'zip -r ${component}-${TAG_NAME}.zip *'

                    sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://nexusipaddress:8081/repository/${component}/${component}-${TAG_NAME}.zip'


//                    echo 'Publish Artifact'
//                    sh 'env'

                }
            }

    }
}