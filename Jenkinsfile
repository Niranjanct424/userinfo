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
			emailext (
                subject: "Build ${currentBuild.result}: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: """<p>Build ${currentBuild.result}: ${env.JOB_NAME} - ${env.BUILD_NUMBER}</p>
                         <p>Commit: ${env.GIT_COMMIT}</p>
                         <p>Duration: ${currentBuild.duration} ms</p>""",
                to: 'niranjangyadav124@gmail.com',
                mimeType: 'text/html'
            )
        }
    }
}