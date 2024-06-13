# Challenge ACL


## Guía para levantar la api 

Para levantar el proyecto desde la consola sigue los siguientes pasos a continuación. Esta documentación incluye cómo clonar el repositorio, configurar base de datos MySQL, construir y ejecutar este proyecto, así como tambien interactuar con la API utilizando Swagger para documentar la API.

## Requisitos previos

- Java 17: Asegúrate de tener Java 17 instalado.
- Maven: Asegúrate de tener Maven instalado.
- MySQL: Asegúrate de tener MySQL instalado y corriendo.

## Pasos para Levantar el Proyecto

1. Clonar el Repositorio
Clona el repositorio en tu máquina local:

- git clone https://github.com/GaedoC/challengeACL.git
- cd ./challengeACL

2. Configura la Base de Datos MySQL.
Crea una base de datos en MySQL, puedes hacerlo utilizando la query que se encuentra en el apartado luego actualiza las credenciales de MySQL y la URL de conexión de MySQL que se encuentra en el archivo resources/application.properties.

- CREATE DATABASE demos;

En application.properties agregue su usuario y contraseña de mysql.
- spring.datasource.url=jdbc:mysql://localhost:3306/demos
- spring.datasource.username=root
- spring.datasource.password=

3. Construir el Proyecto
Construye el proyecto utilizando Maven:

- mvn clean install

4. Ejecutar el Proyecto
Levanta el proyecto utilizando Maven:

- mvn spring-boot:run

## Interactuar con la API (SWAGGER UI)

1. Abre tu navegador y navega a http://localhost:8669/swagger-ui/index.html para ver la documentación Swagger de tu API. Esto te permitirá interactuar con los endpoints de la API directamente desde el navegador y así poder probarlos. Tambien agregue un postman collection .

## Desarrollo Front End

1.Creción de proyecto con REACT y REDUX.

Espero les sea útil . Saludos
Gustavo Andrés Aedo Conejera



