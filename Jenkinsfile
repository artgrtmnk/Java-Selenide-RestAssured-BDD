pipeline{
    agent any
    stages {
        stage ('Build Stage') {
            steps {
                withMaven(maven: 'maven_3_8_6')
                {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }
        stage ('Test Stage') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE')
                {
                    withMaven(maven: 'maven_3_8_6')
                        {
                            sh 'mvn test'
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