node ('master') {
    stage('pull') {
        git 'https://github.com/tomek401273/bibliography-backend.git'
    }
    stage('package-bib') {
        sh script: 'mvn package'
    }
    stage('docker') {
        sh script: 'docker build -t tomek371240/bibl:2.1 .'
    }
    stage('docker-deploy') {
        sh script: 'docker stack deploy -c biblio.yml biblio-stack'
    }
}
