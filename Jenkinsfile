properties([pipelineTriggers([githubPush()])])

pipeline {
    agent {
          label 'jenkins-slave-1'
     }
    triggers {
          pollSCM('4 4 4 * *')
    }
    environment {
          VER_NUM = "1.0.${BUILD_NUMBER}";
          REL_NUM = "1.0.${BUILD_NUMBER}.RELEASE";
          mavenHome =  tool name: "devops-maven", type: "maven"
     }
    tools{
          maven 'devops-maven'
     }
    stages {
           stage ('Git Checkout') {
                 steps {
                     echo pwd;
               checkout([
                 $class: 'GitSCM',
                 branches: [[name: 'master']],
                 userRemoteConfigs: [[
                    url: 'https://github.com/bathuruorg/devops-springbootrest.git',
                    credentialsId: 'github-credentials'
                 ]]
                ])

                }
           }

         stage ('Multiple Builds') {
              parallel {
                  stage ("Maven Build") {
                        steps {
                             echo pwd;
                            sh "${mavenHome}/bin/mvn clean versions:set -Dver=${VER_NUM} package "
                       }
                  }
                  stage ("Gradel Build") {
                        steps {
                            echo "Gradel Build !!!!!!!"
                       }
                  }
              }
          }

         stage ('Nexus Upload') {
                  steps {
                          echo pwd;
                           nexusPublisher  nexusInstanceId: 'AppleNexusRepo',
                           nexusRepositoryId: 'simpleappRepo',
                           packages: [[$class: 'MavenPackage',
                           mavenAssetList: [[classifier: '', extension: '', filePath: "${WORKSPACE}/target/springbootrest-${REL_NUM}.jar"]],
                           mavenCoordinate: [artifactId: 'springbootrest', groupId: 'com.apple', packaging: 'war', version: "${REL_NUM}"]]]
                   }
          }

          stage('Docker Build & Push') {    
                  steps {
                          script{        // To add Scripted Pipeline sentences into a Declarative
                                    try{
                                            sh "echo pwd"
                                             //sh "docker rm -f simpleapp || true"
                                             //sh "docker rmi bathurudocker/simpleapp || true"       //sh 'docker rmi $(docker images bathurudocker/simpleapp)''
                                          }catch(error){
                                          //  do nothing if there is an exception
                                          }
                            }
                          sh "docker build -t bathurudocker/springbootrest:${VER_NUM} ."
                          //withCredentials([string(credentialsId: 'dockerHubPwd', variable: 'dockerpwd')]) {
                            //     sh "docker login -u bathurudocker -p ${dockerpwd}"
                        // }
                          //sh "docker push bathurudocker/simpleapp:${VER_NUM}" 
                 } 
          }
          /*
        stage('SonarQube Analysis') {
             steps {
                    withSonarQubeEnv('SonarQubeServer') {
                        sh "${mavenHome}/bin/mvn sonar:sonar"
                     }
             }
         }*/

          /*
          stage('Build & Push Docker Image') {    
                  steps {
                          script{        // To add Scripted Pipeline sentences into a Declarative
                                    try{
                                             sh "docker rm -f simpleapp || true"
                                             sh "docker rmi bathurudocker/simpleapp || true"       //sh 'docker rmi $(docker images bathurudocker/simpleapp)''
                                          }catch(error){
                                          //  do nothing if there is an exception
                                          }
                            }
                          sh "docker build -t bathurudocker/simpleapp:${VER_NUM} ."
                          /*withCredentials([string(credentialsId: 'dockerHubPwd', variable: 'dockerpwd')]) {
                                 sh "docker login -u bathurudocker -p ${dockerpwd}"
                         }
                          sh "docker push bathurudocker/simpleapp:${VER_NUM}" */
                          //sh  "docker run  -d -p 8010:8080 --name simpleapp bathurudocker/simpleapp:${VER_NUM}"
                 //} 
          //}
          
          stage('Deploy Into TEST') {
          steps{
           sh "pwd"
           sshagent(['docker_server_withkey']) {
               //sh "ssh -o StrictHostKeyChecking=no osboxes@192.168.43.94  sudo docker rm -f simpleapp || true"
               //sh "ssh -o StrictHostKeyChecking=no osboxes@192.168.43.94  sudo docker run  -d -p 8080:8080 --name sr bathurudocker/springbootrest:${VER_NUM}"
               sh "ssh -o StrictHostKeyChecking=no osboxes@192.168.43.94  echo srini"
                sh "ssh -o StrictHostKeyChecking=no osboxes@192.168.43.94 sudo docker run  -d -p 8080:8080 --name sr bathurudocker/springbootrest:1.0.10"
          }
          }
     }     /*
     stage('Deploy Into PROD') {
           sh "pwd"
           sshagent(['Ansible-Server-SSH']) {
               sh "scp -o StrictHostKeyChecking=no simpleapp-deploy-k8s.yaml simpleapp-playbook-k8s.yml ec2-user@13.232.221.131:/home/ec2-user/"
               sh "ssh -o StrictHostKeyChecking=no ec2-user@13.232.221.131 ansible-playbook  -i /etc/ansible/hosts /home/ec2-user/simpleapp-playbook-k8s.yml"
          }
     }*/



    }
    post {
           success {
                echo 'Pipeline finished'
           }
           failure {
                echo 'Pipeline failure'
           }
    }
}
