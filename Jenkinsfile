pipeline{
    agent any
    stages {
        stage ('Compile Stage') {
            steps {
                withMaven(maven: 'maven_4_0_0') {
                    sh 'mvn clean install'
                }
            }
        }
    stage ('Test Stage') {
            steps {
                withMaven(maven: 'maven_4_0_0') {
                    sh 'mvn test'
                }
            }
        }
        stage ('Cucumber Reports') {
            steps {
                cucumber buildStatus: "UNSTABLE",
                    fileIncludePattern: "**/cucumber.json",
                    jsonReportDirectory: 'allure-report'
            }
        }
    }
}