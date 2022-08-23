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
                 withMaven(maven: 'maven') {
                     sh 'mvn clean install -DskipTests'
                 }
             }
         }
         stage ('Testing Stage') {
             steps {
                 withMaven(maven: 'maven') {
                     sh 'mvn clean test'
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
