pipeline {
       environment
{
registry = "khalillaabidii/khalillaabidii"
registryCredential= 'dockerHub'
dockerImage = ''
}
       
       agent any
       
       
stages{
       stage('Checkout GIT'){
       steps{
             echo 'Pulling...';
             git branch: 'main',
            url : 'https://github.com/khalillabidi24/smartup.git';
             }
         }
         
         stage("Test,Build"){
          steps{
          bat """mvn clean package -Dmaven.test.failure.ignore=true"""
          }
          }
          
          stage("Sonar"){
          steps{
          bat """mvn sonar:sonar"""
          }
          }
          
          stage("Nexus"){
          steps{
          bat """mvn clean package -Dmaven.test.failure.ignore=true deploy:deploy-file -DgroupId=com.example -DartifactId=projetCaisse -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://localhost:8082/repository/maven-releases/ -Dfile=target/projetCaisse-1.0.jar"""
           }
           }
     
   
     stage('Building our image') {
    steps {
       script {
          dockerImage= docker.build registry + ":$BUILD_NUMBER" 
       }
    }
  }

  stage('Deploy our image') {
    steps {
       script {
         docker.withRegistry( '', registryCredential) {
            dockerImage.push() 
         }
       } 
    }
  }

  stage('Cleaning up') {
    steps { 
      bat "docker rmi $registry:$BUILD_NUMBER" 
    }
  }

    
       
       
          
        }
      post {
    always {
       mail to: 'khalillabidi24@gmail.com',
          subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
          body: "${env.BUILD_URL} has result ${currentBuild.result}"
    }
  }
      
       }