pipeline {
       environment
{
registry = "khalillaabidi/khalillaabidi"
registryCredential= 'dockerhub'
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
	
	
          
stage ("Verification du  version Maven..."){
			steps{
				bat """mvn -version"""
			}
		}

		stage ("Clean..."){
			steps{
				bat """mvn clean"""
			}
			
		}

		stage ("Creation du livrable..."){
			steps{
				bat """mvn package -Dmaven.test.skip=true"""
			}
		}

		stage ("Lancement des Tests Unitaires..."){
			steps{
				bat """mvn test"""
			}
		}

		stage ("Analyse avec Sonarqube"){
			steps{
				bat """mvn sonar:sonar"""
			}
		}

		stage ("Deploiement dans Nexus..."){
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
	
	
	
	
	
	
	
   
     
    
       stage('Email Notification') {
	steps { 
	    mail bcc: '', body: '''Hello Khalil LAABIDI , this is a Jenkins Pipeline alert for launching Cycle

            Thank you''', cc: '', from: '', replyTo: '', subject: 'Jenking Job Launched', to: 'khalil.laabidi@esprit.tn'
    }
}
        
       
          
        }
      
       }
