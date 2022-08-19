pipeline{
    agent any
    stages {
        stage ('Compile Stage') {
            steps {
                withMaven(maven: 'maven_3_8_6') {
                    sh 'mvn clean install'
                }
            }
        }
        stage ('Test Stage') {
            steps {
                withMaven(maven: 'maven_3_8_6') {
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
        stage ('Allure report') {
            steps {
                allure includeProperties:
                    false,
                    jdk: '',
                    results: [[path: 'allure-report']]
            }
        }
    }
}