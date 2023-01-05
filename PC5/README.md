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
    
    - Pipeline con prueba de aceptación ejecutado exitosamente
    
      ![image-20230104090226840](README.assets/image-20230104090226840.png)
    
  - ### Escribir pruebas orientadas a usuarios

    - ![image-20230104090650413](README.assets/image-20230104090650413.png)
    - 

- ## Actividad 24

  - Instalación de cliente Kubernetes

    ![image-20230104123146580](README.assets/image-20230104123146580.png)

  - Inicialización de minikube

    ![image-20230104124521928](README.assets/image-20230104124521928.png)

  - Información del cluster de kubernetes actual

    ![image-20230104165717138](README.assets/image-20230104165717138.png)

  - Creación de cluster de kubernetes mediante archivo `deployment.yaml`

    ![image-20230104174312558](README.assets/image-20230104174312558.png)

  - Se observan los 3 contenedores creados, como se especificó en el archivo YAML

    ![image-20230104174633825](README.assets/image-20230104174633825.png)

  - Se obtienen los logs de cada contenedor corriendo la aplicación calculator:

    ![image-20230104174722765](README.assets/image-20230104174722765.png)

  - Archivo `service.yaml` para crear el servicio a traves del cual se accederá a los contenedores creados

    ![image-20230104175355126](README.assets/image-20230104175355126.png)

    ![image-20230104180352671](README.assets/image-20230104180352671.png)

  - Se observa que se puede acceder a Calculator a través de la ip de minikube usando el puerto 32731

    ![image-20230104182932874](README.assets/image-20230104182932874.png)

  - ### Kubernetes avanzado

    - Escalando el servicio del proyecto 

      ![image-20230104184142650](README.assets/image-20230104184142650.png)

    - **Actualización de aplicación:** Se modifica el archivo `deployment.yaml` y se vuelve a construir la aplicación. Luego de esto, se observa que se han eliminado los contenedores anteriores.

      ![image-20230104185000601](README.assets/image-20230104185000601.png)

    - **Actualizaciones continuas:** Se busca que cada contenedor se elimine uno por uno y se lanze su nueva version hasta que todos los contenedores antiguos hayan sido reemplazados. Para ello se edita el archivo `deployment.yaml`

      ![image-20230104190249888](README.assets/image-20230104190249888.png)

      Los contenedores se actualizan uno por uno

      ![image-20230104190401947](README.assets/image-20230104190401947.png)

      Se eliminan los recursos creados

      ![image-20230104190448630](README.assets/image-20230104190448630.png)

  - ### Dependencias de la aplicación

    - Se definen los recursos de despliegue y servicio de Hazelcast en el archivo `hazelcast.yaml`

      ![image-20230104191110919](README.assets/image-20230104191110919.png)

      ![image-20230104191059406](README.assets/image-20230104191059406.png)

      ![image-20230104191855910](README.assets/image-20230104191855910.png)

    - ### Implementación del sistema multiaplicación

      - ![image-20230104202855417](README.assets/image-20230104202855417.png)

      - ![image-20230104202913277](README.assets/image-20230104202913277.png)

      - Enviando la imagen con el proyecto compilado a dockerhub

        ![image-20230104205905992](README.assets/image-20230104205905992.png)

      - Ejecución de calculador en minikube

        ![image-20230104205945472](README.assets/image-20230104205945472.png)

      - Probando la solicitud hacia la aplicación Calculador:

        ![image-20230104210023096](README.assets/image-20230104210023096.png)

        