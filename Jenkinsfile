def getGitBranchName() {  
    return scm.branches[0].name
}

def branchName
def targetBranch

pipeline {
  agent any
	environment {
     DOCKERHUB_USERNAME = "amirthameur"
     PROD_TAG = "${DOCKERHUB_USERNAME}/ski:v1.0-prod"
    }
	parameters {
	string(name: 'BRANCH_NAME', defaultValue: "${scm.branches[0].name}", description: 'Git branch name')
        string(name: 'CHANGE_ID', defaultValue: '', description: 'Git change ID for merge requests')
	string(name: 'CHANGE_TARGET', defaultValue: '', description: 'Git change ID for the target merge requests')
    }

  stages {
    stage('Github') {
      steps {
	script {
	branchName = params.BRANCH_NAME
        targetBranch = branchName

          git branch: branchName,
          url: 'https://github.com/OnsZitouni/5INFINI1-G2-StationSKI.git',
          credentialsId: 'cc0398c4-7a27-4cdf-b3f9-f975dca0af18'
      }
	  echo "Current branch name: ${branchName}"
	  echo "Current branch name: ${targetBranch}"
      }
    }
 stage('MVN BUILD') {
      steps {
        sh 'mvn clean install'
        echo 'Build stage done'
      }
    }
	  
stage('MVN COMPILE') {
      steps {
	sh 'mvn compile'
        echo 'Compile stage done'
      }
    }

  stage ('JUNIT / Mockito TEST') {
      steps {
        sh 'mvn test'
        echo 'test stage done'
      }
    }
	  
//stage("SonarQube ") {
     //    steps {
       //      withSonarQubeEnv('sonarqube') {
         //           sh 'mvn sonar:sonar'
          //    }
    //       }
    //    }

stage('SonarQube Analysis') {
            steps {
                // This step runs the SonarQube analysis
                sh 'mvn sonar:sonar \
                    -Dsonar.projectKey=StationSki \
                    -Dsonar.host.url=http://192.168.162.222:9000/ \
                    -Dsonar.login=sqa_63225c002bc2854e08a3a0c7f4835317723afad6'
            }
        }
	  

	  
stage ('NEXUS DEPLOY') {
      steps {
     sh 'mvn deploy -DskipTests'
      }
    }

	//  stage('Deploy to Nexus') {
        //    steps {
        //        sh 'mvn deploy'
         //   }
        //}

	  

  stage('Build Docker') {
    steps {
        script {   
                sh "docker build -t ${PROD_TAG} ."
        }
    }
}

	  stage('Docker Login'){
            steps{
                withCredentials([usernamePassword(credentialsId: 'b31f1212-972e-4b38-8bd6-83d716a6e871', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
    }
  }

        }

	  stage('Docker Push'){
            steps{
                sh 'docker push $DOCKERHUB_USERNAME/ski --all-tags '
            }
        }



  stage('DOCKER COMPOSE') {
    steps {
	sh "docker-compose down -v"
        sh "docker-compose -f docker-compose.yml up -d"
    }
	}




	  
  }

}
