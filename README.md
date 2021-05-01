# spring-boot-with-angular
This is a sample Spring Boot with Angular Project. This sample include: -
- Create sample spring boot project (app-server project)
- Create sample angular project (app-client project)
- Create profile for development and production environment (to configure Cross-origin resource sharing (CORS)) 

## What's this project do
1. app-server: Expose RESTful Rest api: "/api/message". This api will return JSON data: -
```
{"id":-1,"content":"This is DataMessage"}
```
2. app-client: Consume api: "/api/message". And display data in browser as: -
```
ID: -1
Content: This is DataMessage
```

## Instruction to create this sample project
Note: To clone and build this project, please skip to item 4.

1. Create app-server
	- create spring boot project
		- https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.4.5.RELEASE&packaging=jar&jvmVersion=11&groupId=com.example&artifactId=app-server&name=app-server&description=Sample%20project%20for%20Spring%20Boot%20with%20Angular&packageName=com.example.app-server&dependencies=web
	- test run
		- cmd (app-server folder): mvnw spring-boot:run
	- create rest controller
		- create message class : [DataMessage.java](https://github.com/kritdev/spring-boot-with-angular/blob/spring-boot-with-angular/app-server/src/main/java/com/example/appserver/rest/model/DataMessage.java)
		- create rest controller class : [SimpleRestController.java](https://github.com/kritdev/spring-boot-with-angular/blob/spring-boot-with-angular/app-server/src/main/java/com/example/appserver/rest/SimpleRestController.java)
		- (optional) test => cmd: curl http://localhost:8080/api/message
	- create applicationConfiguration
		- create corsFilter bean: [WebConfigurer](https://github.com/kritdev/spring-boot-with-angular/blob/spring-boot-with-angular/app-server/src/main/java/com/example/appserver/config/WebConfigurer.java)
		- create default application property file: [application.properties](https://github.com/kritdev/spring-boot-with-angular/blob/spring-boot-with-angular/app-server/src/main/resources/application.properties)
		- create default application property file: [application-prod.properties](https://github.com/kritdev/spring-boot-with-angular/blob/spring-boot-with-angular/app-server/src/main/resources/application-prod.properties)
		- (optional) run app-server
			- cmd (app-server folder): mvnw spring-boot:run
			- cmd (app-server folder): mvnw spring-boot:run -Dspring-boot.run.profiles=prod

2. Create rest-client
	- create rest-client project
		- cmd (root folder): ng new app-client
	- create DataService
		- cmd (app-client folder): ng g service data
		- modify [data.service.ts](https://github.com/kritdev/spring-boot-with-angular/blob/spring-boot-with-angular/app-client/src/app/data.service.ts)
	- configuring application environments
		- modify [environment.ts](https://github.com/kritdev/spring-boot-with-angular/blob/spring-boot-with-angular/app-client/src/environments/environment.ts)
		- modify [environment.prod.ts](https://github.com/kritdev/spring-boot-with-angular/blob/spring-boot-with-angular/app-client/src/environments/environment.prod.ts)
	- consume DataService
		- modify [app.component.ts](https://github.com/kritdev/spring-boot-with-angular/blob/spring-boot-with-angular/app-client/src/app/app.component.ts)
		- modify [app.component.html](https://github.com/kritdev/spring-boot-with-angular/blob/spring-boot-with-angular/app-client/src/app/app.component.html)

3. Build Application (Spring boot and Angular)
	- app-server
		- cmd (app-server folder): mvnw package
	- app-client
		- cmd (app-client folder): ng build --prod

4. Deploy Application
	- Copy to file into production environment
```
/application folder
|-- rest-service-cors-0.0.1-SNAPSHOT.jar
|-- public
|   |-- index.html
|   |-- favicon.ico
|   |-- main.xxxxxxx.js
|   |-- polyfills.xxxxxxx.js
|   |-- runtime.xxxxxxx.js
|   |-- styles.xxxxxxx.css
```

5. Start Application
	- cmd (application folder): java -cp . -jar app-server-0.0.1-SNAPSHOT.jar  --spring.profiles.active=prod
	- browse to : http://[production ip or domain]:8080/
