def call () {

    node('docker') {

        stage('Code checkout' ) {
            sh 'find . | grep "^./" | xargs rm -rf'
            sh 'env'
            if(env.TAG_NAME ==~ ".*"){
                env.gitbrname = "refs/tags/${env.TAG_NAME}"
                sh 'echo inside tag name'
            } else {
                env.gitbrname = "${env.BRANCH_NAME}"
                sh 'echo inside branch '
            }
            checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[url: "https://github.com/narasimhavrm/${env.component}.git" ]], branches: [[name: gitbrname]], poll: false]
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

        stage ('Docker file') {
            echo 'inside docker file'
          sh 'docker build -t ${component} .'

      }


            if(env.TAG_NAME ==~ ".*") {
                stage('Publish Artifact ') {
//                    if (env.cibuild == "java") {
//                        sh 'mv target/${component}-1.0.jar ${component.jar}'
//                        sh 'rm -rf pom.xml src target'
//                    }
//                    sh 'rm -rf Jenkinsfile'
//                    sh 'echo ${TAG_NAME} > VERSION'
//                    sh 'zip -r ${component}-${TAG_NAME}.zip *'
//
//                    sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://172.31.86.240:8081/repository/${component}/${component}-${TAG_NAME}.zip'


//                    echo 'Publish Artifact'
//                    sh 'env'

                }
            }

        if(env.TAG_NAME ==~ ".*") {
            stage('Publish Artifact ') {
                sh 'docker tag ${component}:latest 079329262703.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME}'
                sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 079329262703.dkr.ecr.us-east-1.amazonaws.com'
                sh 'docker push 079329262703.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME}'
            }

    }
}
}