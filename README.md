# ChallengeACL

## Guía para levantar la api 

Para levantar el proyecto desde la consola y entender los pormenores del mismo, sigue los pasos a continuación. La documentación incluirá cómo clonar el repositorio, configurar la base de datos MySQL, construir y ejecutar el proyecto, así como interactuar con la API utilizando curl y cómo acceder a Swagger UI para la documentación de la API.


## Requisitos previos

Java 17: Asegúrate de tener Java 17 instalado.
Maven: Asegúrate de tener Maven instalado.
MySQL: Asegúrate de tener MySQL instalado y corriendo.

## Pasos para Levantar el Proyecto

1. Clonar el Repositorio
Clona el repositorio en tu máquina local:

- git clone https://github.com/GaedoC/challengeACL.git
- cd ./challengeACL

2. Configurar la Base de Datos MySQL
Crea una base de datos en MySQL. Puedes hacerlo utilizando el siguiente comando MySQL y actualiza las credenciales y la URL de conexión en el archivo, src/main/resources/application.properties

- CREATE DATABASE demos;
- ![image](https://github.com/GaedoC/challengeACL/assets/17816969/dc278f20-8968-4ac3-b6b5-63dec955e4c8)

3. Construir el Proyecto
Construye el proyecto utilizando Maven:

- mvn clean install

4. Ejecutar el Proyecto
Levanta el proyecto utilizando Maven:

- mvn spring-boot:run

## Interactuar con la API (SWAGGER UI)

1. Abre tu navegador y navega a http://localhost:8080/swagger-ui/index.html para ver la documentación Swagger de tu API. Esto te permitirá interactuar con los endpoints de la API directamente desde el navegador y así poder probarlos. Tambien agregue un postman collection .

Espero les sea útil . Saludos
