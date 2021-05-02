# spring-data
This is a sample to demonstrate the integration of spring data into spring booth with angular project. This sample include: -
- Create connection from spring boot project to MySQL database.
- Create CRUD RESTful APIs in spring boot project. And handle exception in the rest controller by using [org.springframework.web.server.ResponseStatusException](https://www.baeldung.com/spring-response-status-exception).
- Create Angular service to consume CRUD RESTful APIs.

Note:<br>
From [Spring Boot 2.3 Release Notes](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.3-Release-Notes#changes-to-the-default-error-pages-content):
`
The error message and any binding errors are no longer included in the default error page by default. This reduces the risk of leaking information to a client. server.error.include-message and server.error.include-binding-errors can be used to control the inclusion of the message and binding errors respectively. Supported values are always, on-param, and never.
`

## Instruction to create this sample project

#### 1. Prerequisite
- Checkout [spring-boot-with-angular](https://github.com/kritdev/spring-boot-with-angular/tree/spring-boot-with-angular) branch.
- Install MySQL Database. And create an empty database (default name is sample). (The database configuration can be changed in app-server/src/main/resources/application.properties)

#### 2. app-server
- Update pom.xml. Adding dependency as below: -
```
      <dependency> 
        <groupId>org.springframework.boot</groupId> 
        <artifactId>spring-boot-starter-data-jpa</artifactId> 
      </dependency> 
      
      <dependency>
	     <groupId>javax.validation</groupId>
	     <artifactId>validation-api</artifactId>
      </dependency>
      
      <dependency> 
        <groupId>mysql</groupId> 
        <artifactId>mysql-connector-java</artifactId> 
      </dependency>
```

- Update application.properties. Adding these lines:-
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/sample
spring.datasource.username=admin
spring.datasource.password=admin
```
Note: update setting as your mysql configuration
- Create Table structure by updating [DataMessage.java](https://github.com/kritdev/spring-boot-with-angular/blob/spring-data/app-server/src/main/java/com/example/appserver/rest/model/DataMessage.java).
- Create repository for retrieving and updating data in database. [DataMessageRepository.java](https://github.com/kritdev/spring-boot-with-angular/blob/spring-data/app-server/src/main/java/com/example/appserver/repository/DataMessageRepository.java).
- Create [AppResponseUtil.java](https://github.com/kritdev/spring-boot-with-angular/blob/spring-data/app-server/src/main/java/com/example/appserver/rest/AppResponseUtil.java) for handling data not found exception and response http error status to app-client.
- Update [SimpleRestController.java](https://github.com/kritdev/spring-boot-with-angular/blob/spring-data/app-server/src/main/java/com/example/appserver/rest/SimpleRestController.java) for providing rest methods (GET, POST, PUT, DELETE).
#### 3. app-client 
- Update [data.service.ts](https://github.com/kritdev/spring-boot-with-angular/blob/spring-data/app-client/src/app/data.service.ts) for consuming app-server's rest methods.
- Update [app.module.ts](https://github.com/kritdev/spring-boot-with-angular/blob/spring-data/app-client/src/app/app.module.ts) by importing FormsModule (for using ngModel in app.component.html.
- Update [app.component.ts](https://github.com/kritdev/spring-boot-with-angular/blob/spring-data/app-client/src/app/app.component.ts) and [app.component.html](https://github.com/kritdev/spring-boot-with-angular/blob/spring-data/app-client/src/app/app.component.html) for displaying and updating data in browser.
