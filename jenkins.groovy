node ('master') {
    stage('pull') {
        git 'https://github.com/tomek401273/bibliography-backend.git'
    }
    stage('docker') {
        sh script: 'docker build -t tomek371240/bibl:2.2 .'
    }
    stage('docker-deploy') {
        sh script: 'docker stack deploy -c biblio.yml biblio-stack'
    }
}
