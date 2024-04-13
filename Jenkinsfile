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
                subject: "Pipeline Status: ${BUILD_NUMBER}",
                body: '''<html>
<head>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      padding: 20px;
    }
    .container {
      max-width: 600px;
      margin: auto;
      background: #fff;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h1 {
      color: #333;
      text-align: center;
    }
    p {
      color: #666;
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>New Build Trigger Status</h1>
    <p><strong>Build Number:</strong> ${BUILD_NUMBER}</p>
    <p><strong>Build Status:</strong> ${BUILD_STATUS}</p>
    <p><strong>Build URL:</strong> <a href="${BUILD_URL}">${BUILD_URL}</a></p>
  </div>
</body>
</html>''',
                to: 'niranjangyadav124@gmail.com',
                mimeType: 'text/html'
            )
        }
    }
}