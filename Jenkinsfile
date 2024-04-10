pipeline{
    agent any
    tools {
        maven "maven"
    }
    
    stages{
        stage("SCM checkout"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Niranjanct424/userinfo.git']])
            }
        }
        
        stage("Build Process"){
            steps{
                script{
                    sh 'mvn clean install'
                }
            }
        }
    }
    
    post{
        always{
            emailext body: '''<html>
<body>
<div>
<h1> New Build Trigger Status </h1>
</div>
<p>Build Number : ${BUILD_NUMBER}</p>
<p>Build Status : ${BUILD_STATUS}</p>
<p>Build URL :  ${BUILD_URL}</p>
</body>
</html>
''', subject: 'Pipeline status : ${BUILD_NUMBER}', to: 'niranjangyadav124@gmail.com'
        }
    }
}