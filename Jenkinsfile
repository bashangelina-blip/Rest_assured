pipeline {
    agent any

    stages {
        stage('Build & Test') {
            steps {
                // Використовуємо команду для Windows, оскільки твій Jenkins локальний
                bat 'mvn clean test'
            }
        }
    }
}