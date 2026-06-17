pipeline {
    agent any
    tools{
    jdk 'Java'
    }

    // Тут ти створюєш випадаюче меню в Jenkins
    parameters {
        choice(name: 'TEST_GROUP', choices: ['smoke', 'regression', 'all'], description: 'Який набір тестів запустити?')
    }

    stages {
        stage('Test') {
            steps {
                script {
                    // Логіка: що обрали в меню — ту команду і запускаємо
                    def mavenCommand = 'mvn clean test'

                    if (params.TEST_GROUP == 'smoke') {
                        mavenCommand = 'mvn clean test -Dgroups=smoke'
                    } else if (params.TEST_GROUP == 'regression') {
                        mavenCommand = 'mvn clean test -Dgroups=regression'
                    }

                    bat mavenCommand
                }
            }
        }
    }


    post {
        always {
            // Jenkins автоматично візьме файли з цієї папки і зробить звіт
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
        }
    }
}