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

2. 2. Configurar la Base de Datos MySQL
Crea una base de datos en MySQL. Puedes hacerlo utilizando el siguiente comando MySQL:

- CREATE DATABASE demos;

Actualiza las credenciales y la URL de conexión en el archivo, src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/tasks_db.
spring.datasource.username=tu_usuario.
spring.datasource.password=tu_contraseña.
spring.jpa.hibernate.ddl-auto=update.

