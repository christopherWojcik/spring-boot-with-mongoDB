[![CC0](https://img.shields.io/github/languages/code-size/christopherWojcik/spring-boot-with-mongoDB?style=plastic)]()
![GitHub last commit](https://img.shields.io/github/last-commit/christopherWojcik/spring-boot-with-mongoDB?style=plastic) </br>


## Simple REST application with MongoDB

### Running the app
1. Firstly run Mongo server with default port: <code>127.0.0.1:27017</code></br>
- locally by running <path_to_mongoDB>\Server\<version_number>bin]mongo.exe
- by docker - [`link`](https://hub.docker.com/_/mongo)
2. Run the Spring Boot project: `mvn spring-boot:run`


Stuff/stack used in this project:
- Java 11 and Spring Boot/Data/MVC with some tests
- MongoDB as a NoSQL DB
- Spring Security - JWT Authorization
- Swagger2
- AOP - very simple usage but works
- Custom exceptions


### Swagger 2.0 documentation
In Chrome browser - install JsonViewer for better reading :)</br>
[<code>localhost:8080/v2/api-docs</code>][swagger-docs-url]
</br>

### Swagger-UI:
[<code>localhost:8080/swagger-ui.html</code>][swagger-ui-url]


[swagger-docs-url]: http://localhost:8080v2/api-docs
[swagger-ui-url]: http://localhost:8080/swagger-ui.html

### License
[![](https://img.shields.io/badge/License-MIT-brightgreen)](https://creativecommons.org/publicdomain/zero/1.0/)