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
  }
}
