pipeline{
     agent any
     stages {
         stage ('Passing token') {
             steps {
                 script{
                     def jsonFileString = readFile file: "${WORKSPACE}/token.json"
                     jsonFileString = jsonFileString.replaceAll("YOUR_TOKEN", params.token)
                     writeFile file: "${WORKSPACE}/token.json", text: jsonFileString
                 }
             }
         }
         stage ('Build Stage') {
             steps {
                 withMaven(maven: 'maven_3_8_6') {
                     sh 'mvn clean install -DskipTests'
                 }
             }
         }
         stage ('API Testing Stage') {
             steps {
                 catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                     withMaven(maven: 'maven_3_8_6') {
                             sh 'mvn clean test -D"cucumber.filter.tags=@api"'
                     }
                 }
             }
         }
         stage ('UI Testing Stage') {
                      steps {
                          catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                              withMaven(maven: 'maven_3_8_6') {
                                      sh 'mvn clean test -D"cucumber.filter.tags=@ui"'
                              }
                          }
                      }
                  }
         stage ('Allure report Stage') {
             steps {
                 allure includeProperties: false,
                     jdk: '',
                     results: [[path: 'allure-results']]
             }
         }
     }
 }