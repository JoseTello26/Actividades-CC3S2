# Práctica Calificada N° 5

## Pregunta 1

- ## Actividad 23

  - Creación de imagen de docker-registry

    ![image-20230103222109826](README.assets/image-20230103222109826.png)

  - Creación de restricción de acceso

    ![image-20230103222049467](README.assets/image-20230103222049467.png)

  - Creando imagen docker

    ![image-20230103224433338](README.assets/image-20230103224433338.png)

  - Subiendo imagen a dockerhub

    ![image-20230103225012263](README.assets/image-20230103225012263.png)

  - ### Pruebas de aceptación en Jenkins

    - Creacion de Dockerfile que usa el archivo `.jar` del proyecto `calculator` para crear un contenedor con la aplicación.

      ![image-20230103225851192](README.assets/image-20230103225851192.png)

    - Contenedor docker construido satisfactoriamente

      ![image-20230103230053777](README.assets/image-20230103230053777.png)

    - Verificación a través del navegador de que la aplicación se ejecuta correctamente

      ![image-20230103230147322](README.assets/image-20230103230147322.png)

    - Contenido actual del archivo Jenkinsfile con todas las etapas del pipeline:

      ```
      pipeline {
      	agent any
      	stages {
      		stage("Compile") {
      			steps {
      				dir("calculator"){
      					sh "JAVA_HOME='/usr/lib/jvm/java-17-openjdk-amd64' mvn compile"
      				}
      			}
      		}
      		stage("Unit test") {
      			steps { 
      				dir("calculator"){
      					sh "JAVA_HOME='/usr/lib/jvm/java-17-openjdk-amd64' mvn test"
      				}
      			}
      		}
      		stage("Code coverage") {
      			steps {
      				publishHTML (target: [
      					reportDir: 'calculator/target/site/jacoco',
      					reportFiles: 'index.html',
      					reportName:"JaCoCo Report"
      				])
      			}
      		}
      		stage("Static code analysis") {
      			steps {
      				dir("calculator"){
      					sh "JAVA_HOME='/usr/lib/jvm/java-17-openjdk-amd64' mvn checkstyle:checkstyle"
      				}
      				publishHTML (target: [
      					reportDir: 'calculator/target/site',
      					reportFiles: 'checkstyle.html',
      					reportName:"Checkstyle Report"
      				])
      			}
      		}
      		stage("Package") {
      			steps {
      				dir("calculator"){
      					sh "JAVA_HOME='/usr/lib/jvm/java-17-openjdk-amd64' mvn package"
      				}
      			}
      		}
      		stage("Docker build") {
      			steps {
      				sh "docker build -t checha/calculator ."
      			}
      		}
      	}
      	post {
      		always {
      			mail to: 'jose.tello.l@uni.pe',
      			subject:"Completed Pipeline: ${currentBuild.fullDisplayName}",
      			body:"Your build completed, please check: ${env.BUILD_URL}"
      		}
      	}
      }
      ```

    - Pipeline ejecutado exitosamente siguiendo las etapas correspondientes
    
      ![image-20230104012305777](README.assets/image-20230104012305777.png)
    
    - Creando la prueba de aceptación para Calculator
    
      ![image-20230104012530836](README.assets/image-20230104012530836.png)
    
    - 

- ## Actividad 24