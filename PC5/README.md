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

## Pregunta 2

- **Pregunta 1:** Se puede configurar a los agentes con claves SSH de manera directa, configurar una clave SSH dentro de la imagen de cada agente, guardar las credenciales dentro de Jenkins para usarlas luego en el pipeline o copiar la llave SSH a los agentes de Jenkins mediante un plugin al inicio de la construcción del pipeline.
- **Pregunta 2:**
- **Pregunta 3:** Las pruebas de rendimiento prueban la respuesta y estabilidad del sistema. RTT (round-trip time)es la manera mas simple de hacer un test de rendimiento, el cual consiste en enviar una solicitud a un servicio web y medir el tiempo que tarda.
- **Pregunta 4:** Las pruebas de carga son aquellas que examinan el comportamiento del sistema cuando tiene muchas solicitudes a la vez, midiendo el tiempo de respuesta promedio. Su uso es común en la fase de aseguramiento de calidad del despliegue.
- **Pregunta 5:** Las pruebas de estrés determinan cuántos usuarios pueden acceder a un servicio al mismo tiempo. A diferencia de las pruebas de carga, estas pruebas no miden tiempo, sino que buscan el número máximo de usuarios que el sistema puede manejar. Sin embargo, estas pruebas llevan mucho tiempo y mas análisis, por lo que no deberían incluirse en un pipeline de CD.
- **Pregunta 6:** Las pruebas de escalabilidad miden cómo la latencia y la transferencia de datos cambian mientras se van añadiendo mas servicios al sistema. Dado que la ejecución de estas pruebas requiere de mucho análisis entre la relación de servicios añadidos con el tiempo de respuesta del sistema, resulta dificil incluirlo en un despliegue automatizado.
- **Pregunta 7:** Las pruebas de longevidad ejecutan el sistema por un largo tiempo para detectar fluctuaciones en el rendimiento, fugas de memoria o fallos en su estabilidad. Como requieren de una ejecución de larga duración, no tiene sentido incluirlo dentro de un pipeline.
- **Pregunta 8:** Las pruebas de seguridad incluyen aspectos  como autenticación, autorización y asignación de roles. Su realización es crucial para el despliegue, por lo que son requeridos en el pipeline. Pueden elaborarse como pruebas de aceptación en frameworks como BDD (desarrollo basado en el comportamiento)
- **Pregunta 9:** Las pruebas de mantenimiento determinan qué tan fácil es mantener un código, es decir, evalúan la calidad del código, hacen tests de covertura o hacen análisis de código estático.
- **Pregunta 10:** Las pruebas de recuperación miden qué tan rápido se recupera un sistema luego de un fallo de software o de hardware. Chaos Monkey es un ejemplo de una aplicación de las pruebas de seguridad, es una herramienta que detiene contenedores o máquinas virtuales dentro de un entorno de producción de manera aleatoria. Es asi que los desarrolladores se concentran mas en hacer su sistema mas resiliente a cualquier tipo de fallos

### Control de versiones en el pipeline de Jenkins

- Contenido del Jenkinsfile luego de aplicar la variable de entorno `${BUILD_TIMESTAMP}` a las etiquetas de las imagenes de Docker

  ```groovy
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
  				sh "whoami"
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
  				sh "docker build -t josetello26/calculator:${BUILD_TIMESTAMP} ."
  			}
  		}
  		stage("Docker push") {
  			steps {
  				sh "docker tag calculador josetello26/calculator:${BUILD_TIMESTAMP}"
  				sh "docker push josetello26/calculator:${BUILD_TIMESTAMP}"
  			}
  		}
  		stage("Deploy to staging") {
  			steps {
  				sh "docker run -d --rm -p 8765:8080 --name calculator josetello26/calculator:${BUILD_TIMESTAMP}"
  			}
  		}
  		stage("Acceptance test") {
  			steps {
  				sleep 60
  				sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
  			}
  		}
  	}
  	post {
  		always {
  			sh "docker stop calculator"
  		}
  	}
  }
  ```

  ![image-20230105151611370](README.assets/image-20230105151611370.png)

### Inventario

- Se crean dos clusters con Minikube: Uno llamado "production" que servirá para el código listo para lanzarse, y el otro "staging", para el código en estado de prueba

- Archivo de configuración de los clusteres "production" y "staging"

  ![image-20230106021433005](README.assets/image-20230106021433005.png)

### Versionado

- Las imágenes de docker se suben con marcas de tiempo de compilación del pipeline

- Actualización en Jenkinsfile

  ```groovy
  stage("Docker push") {
  			steps {
  				sh "docker tag calculador josetello26/calculator:${BUILD_TIMESTAMP}"
  				sh "docker push josetello26/calculator:${BUILD_TIMESTAMP}"
  			}
  		}
  ```

- Archivo `deployment.yaml` actualizado para usar el versionamiento:

  ![image-20230106031247204](README.assets/image-20230106031247204.png)

### Entorno staging remoto

- Se ejecutan los nodos configurados en el agente Jenkins

- Actualización en Jenkinsfile:

  ```groovy
  stage("Deploy to staging") {
  			steps {
  				sh "kubectl config get-contexts"
  				sh "kubectl config use-context staging"
  				sh "kubectl apply -f hazelcast.yaml"
  				sh "kubectl apply -f deployment.yaml"
  				sh "kubectl apply -f service.yaml"
  			}
  		}
  ```

### Entorno de pruebas de aceptación

- Se ejecuta la prueba de aceptación de calculador en los contenedores de kubernetes

- Actualización de Jenkinsfile:

  ```groovy
  stage("Acceptance test") {
  			steps {
  				sleep 60
  				sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
  			}
  		}
  ```

- Actualización de `acceptance_test.sh`

  ![image-20230106031547673](README.assets/image-20230106031547673.png)

### Lanzamiento

- Una vez aprobada la prueba de aceptación, se cambia el contexto de los clusters al de producción y se procede a modificar dicho entorno.

- Actualización de Jenkinsfile:

  ```groovy
  stage("Release") {
  			steps {
  				sh "kubectl config use-context production"
  				sh "kubectl apply -f hazelcast.yaml"
  				sh "kubectl apply -f deployment.yaml"
  				sh "kubectl apply -f service.yaml"
  			}
  		}
  ```

- 

Archivo Jenkinsfile final:

```groovy
pipeline {
	agent any
	stages {
		stage("Compile") {
			steps {
				dir("calculator"){
					sh "kubectl config get-contexts"
					sh "chmod +x mvnw"
					sh "./mvnw compile"
				}
			}
		}
		stage("Unit test") {
			steps { 
				sh "whoami"
				dir("calculator"){
					sh "./mvnw test"
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
					sh "./mvnw checkstyle:checkstyle"
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
					sh "./mvnw package"
				}
			}
		}
		stage("Docker build") {
			steps {
				sh "docker build -t josetello26/calculator:${BUILD_TIMESTAMP} ."
			}
		}
		stage("Docker push") {
			steps {
				sh "docker tag calculador josetello26/calculator:${BUILD_TIMESTAMP}"
				sh "docker push josetello26/calculator:${BUILD_TIMESTAMP}"
			}
		}
		stage("Deploy to staging") {
			steps {
				sh "kubectl config get-contexts"
				sh "kubectl config use-context staging"
				sh "kubectl apply -f hazelcast.yaml"
				sh "kubectl apply -f deployment.yaml"
				sh "kubectl apply -f service.yaml"
			}
		}

		stage("Acceptance test") {
			steps {
				sleep 60
				sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
			}
		}
        stage("Release") {
			steps {
				sh "kubectl config use-context production"
				sh "kubectl apply -f hazelcast.yaml"
				sh "kubectl apply -f deployment.yaml"
				sh "kubectl apply -f service.yaml"
			}
		}
	}
	post {
		always {
			sh "docker stop calculator"
		}
	}
}
```

