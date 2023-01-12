# Actividad 25

## Introducción a la entrega continua

1. Las fases son:
   - **Desarrollo**: Los desarrolladores trabajan en el producto
   - **Aseguramiento de Calidad:** Implementa pruebas de integración, pruebas de aceptación y análisis de requisitos no funcionales.
   - **Operaciones:** Se pasa el código a el despliegue en el entorno de producción.
2. Las fases de un pipeline son:
   - **Integración continua:** Verifica que las partes del código escritas por distintos desarrolladores funcionen correctamente.
   - **Pruebas de aceptación:** Verifica que los requerimientos del cliente hayan sido implementados.
   - **Administración de la configuración:** Configura el entorno de despliegue y despliega el software
3. - Provee de un tiempo de entrega y despliegue a los clientes
   - Provee de la posibilidad de tener retroalimentación de manera mas rápida cada vez que se lanza una nueva versión a producción
   - La repetición del proceso de despliegue hace que este sea un proceso mas seguro
4. - Pruebas de aceptación
   - Pruebas unitarias
   - Pruebas exploratorias
   - Pruebas No-Funcionales
5. Los tests de integración prueban a conexión entre los módulos de una aplicación. Son escritos de manera similar a la de las pruebas de aceptación, pero requieren mas tecnicismos y pruebas de módulos externos e internos. En el pipeline CD, e agregan como una fase aparte
6. La cultura DevOps significa que un solo equipo se encargue de las tres fases de entrega continua. Esto se logra sin perder productividad gracias a la automatización y los pipelines, que llevan tareas de QA y operaciones al equipo de desarrollo.

## Docker

1. Los contenedores, a diferencia de las máquinas virtuales, no requieren de un sistema operativo de base para funcionar, sino que interactúan directamente con el sistema operativo de la máquina física, y aprovechan los recursos de la misma, asi se logra un mejor rendimiento.

2. - Los contenedores Docker evitan todo el proceso de instalación y configuración de un entorno nuevo y lo reducen a simples instrucciones en el Dockerfile
   - Los contenedores mantienen los entornos limpios e independientes uno de otro, a diferencia de trabajar con muchas aplicaciones en un solo entorno

3. Docker tiene aplicaciones nativas en Linux, Windows y macOS, sin embargo, el docker engine está basado en el kernel de Linux, por lo que para su ejecución en macOS y Windows usa máquinas virtuales.

4. Una imagen en docker es una colección de archivos e instrucciones necesarias sin estado para construir un contenedor. Un contenedor es una instancia en ejecución de una imagen, la cual si posee estados.

5. Las imágenes poseen capas, es decir, se pueden construir imágenes a partir de otras.

6. Las imágenes docker se pueden crear de las siguientes maneras:

   - `docker commit`: Crea una imagen a partir de un contenedor activo en docker
   - `Dockerfile`: Crea una imagen de docker manualmente, a traves de instrucciones dentro de un archivo `Dockerfile`

7. Para crear una imagen a partir de un dockerfile se usa el comando 

   ```bash
   docker build -t <nombre_imagen> .
   ```

8. Para crear un contenedor desde una imagen de docker se usa el comando:

   ```bash
   docker run <nombre_imagen>
   ```

9. Publicar un puerto significa realizar un mapeo desde el host de Docker hacia el puerto del contenedor, haciendolo accesible a través de ese puerto

10. Un volumen en Docker es un directorio del host que ha sido montado en el contenedor, haciendo posible la persistencia de datos en dicho directorio, es decir, el contenedor y el host comparten archivos dentro de ese directorio

## Jenkins

1. Si, se puede encontrar como imagen oficial de jenkins
2. El maestro Jenkins e encarga de orquestrar las instrucciones de un pipeline, mientras que el agente esclavo solo ejecuta los procesos que se le mande.
3. El escalado vertical implica darle mas recursos a un maestro Jenkins de manera que todo proceso se concentre en ese maestro, mientras que un escalado horizontal implica tener mas instancias de maestro Jenkins, haciendo que tengan distintas configuraciones y permite resiliciencia del sistema, es decir, si un maestro falla, los otros aun funcionan.
4. Se pueden comunicar mediante dos protocolos:
   - **SSH:** El maestro se conecta a los agentes mediante el protocolo SSH, el cual debe estar instalado en los agentes
   - **Java web start:** Una aplicación de Java se inicia en cada agente y se establece una conexión TCP entre el cliente y el maestro
5. Un agente permanente de Jenkins ejecuta todos los builds del pipeline de manera nativa, mientras que un agente Docker puede crear contenedores específicos dentro de los cuales se pueden ejecutar los builds.
6. Cuando se quieran ejecutar builds con requerimientos específicos que no vienen por defecto en el entorno del agente de Jenkins
7. Cuando se quieran ejecutar builds dentro del maestro y se requiera ajustar los requerimientos del entorno del mismo.

## Pipelines

1. Un pipeline es una secuencia automatizada de operaciones, que provee de la entrega y el aseguramiento de la calidad de un software
2. Un step es una operación que le dice a Jenkins qué hacer, mientras que un stage es una separacion de pasos agrupados secuencialmente
3. La sección post de un pipeline define una serie de instrucciones que serán ejecutadas al final de la construcción del pipeline
4. Las fases mas importantes de un pipeline son:
   - Checkout, donde se descarga el código fuente del repositorio 
   - Compile, donde se compila el código
   - Unit test, se realizan las pruebas unitarias respectivas
5. Un Jenkinsfile es un archivo que contiene instrucciones de pipeline que le indican a Jenkins qué hacer.
6. Se añade la etapa de Code Coverage para asegurar que buena parte del código ha sido probada. Además, se puede hacer que el pipeline falle si no se ha probado buena parte del código y así evitar hacer commit a código sin probar
7. - **External:** La construcción del pipeline inicia gracias al notificador, que puede llamar al trigger debido a la construcción de otro pipelinne o a un script remoto
   - **Polling SCM:** Jenkins llama periódicamente a Github y verifica si hubo algún push, luego inicia la construcción del pipeline.
8. - **Email:** Jenkins envía un correo con los datos de la construcción del pipeline
   - **Chats grupales:** Jenkins envía el status de la construcción a algún chat grupal
   - **Espacios de equipo:** Aprovechan el desarrollo en equipo de manera presencial para asegurarse que todos sepan el estatus de la construcción del pipeline
9. - **Trunk based workflow:** Cada miembro de un equipo clona el repositorio central, el cual tiene solo un acceso. Los cambios son enviados a esa misma rama.
   - **Branching workflow:** Cuando los desarrolladores insertan una nueva característica al software, lo hacen en una rama nueva, la cual una vez probada, se une a la rama principal
   - **Forking workflow:** Cada desarrollador tiene su propia copia del repositorio, y cuando quieren integrar sus códigos, hacen solicitues de pull entre cada uno.

## Pruebas de aceptación automatizadas

1. Docker Registry es un almacenamiento para imagenes de docker, es un servidor que permite que las imágenes sean publicadas (push) o recibidas (pulled)
2. DockerHub es un registro de imágenes oficial basado en la nube.
3. Se nombran las imágenes con el siguiente formato: `<dirección_De_registro>/<nombre_de_imagen>:<tag>`
4. El entorno staging es en donde s