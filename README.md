# spring-boot-with-angular (with Spring Security)
This is a sample Spring Boot with Angular Project, that implement on top of [spring-boot-with-angular](https://github.com/kritdev/spring-boot-with-angular/tree/spring-boot-with-angular) branch. This project demonstrates the implementation of JWT authentication (stateless, with a token), by using Spring Security.

## Instruction to create this sample project
1. Prerequisite
- Checkout [spring-boot-with-angular](https://github.com/kritdev/spring-boot-with-angular/tree/spring-boot-with-angular) branch.
- Install MySQL Database. And create an empty database (default name is sample). (The database configuration can be changed in app-server/src/main/resources/application.properties)
2. app-server
- Copy security folder of this app-server from this branch into the project (src/main/java/com/example/appserver/security)
- Update pom.xml. Adding dependency as below: -
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
      	<dependency> 
        	<groupId>org.springframework.boot</groupId> 
        	<artifactId>spring-boot-starter-data-jpa</artifactId> 
      	</dependency> 
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.hibernate.validator</groupId>
            <artifactId>hibernate-validator</artifactId>
        </dependency>
      	<dependency> 
	        <groupId>mysql</groupId> 
	        <artifactId>mysql-connector-java</artifactId> 
   	  	</dependency>
        <dependency>
            <groupId>org.zalando</groupId>
            <artifactId>problem-spring-web</artifactId>
            <version>0.25.2</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.1</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.1</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.1</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
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

3. app-client 
- Copy security folder from app-client folder of this branch into the project (src/app/security)
- Update AppModule (app.module.ts).
- Update AppComponent (app.component.ts, app.component.html, and app.component.css)
