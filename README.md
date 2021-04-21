# spring-boot-with-angular
This is a sample Spring Boot with Angular Project. This sample include: -
- Create sample spring boot project (app-server project)
- Create sample angular project (app-client project)
- Create profile for development and production environment (to configure Cross-origin resource sharing (CORS)) 

## Instruction to create this sample project
Note: To clone and build this project, please skip to item 4.

1. Create app-server
	- create spring boot project
		- https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.4.5.RELEASE&packaging=jar&jvmVersion=11&groupId=com.example&artifactId=app-server&name=app-server&description=Sample%20project%20for%20Spring%20Boot%20with%20Angular&packageName=com.example.app-server&dependencies=web
	- test run
		- cmd (app-server folder): mvnw spring-boot:run
	- create rest controller
		- (optional) test => cmd: curl http://localhost:8080/api/message
	- create applicationConfiguration
		- create src/main/resources/application.properties
		- create src/main/resources/application-prod.properties
		- (optional) run app-server
			- cmd (app-server folder): mvnw spring-boot:run
			- cmd (app-server folder): mvnw spring-boot:run -Dspring-boot.run.profiles=prod

2. Create rest-client
	- create rest-client project
		- cmd (root folder): ng new app-client
	- create DataService
		- cmd (app-client folder): ng g service data
	- consume DataService
	- configuring application environments

3. Build Application (Spring boot and Angular)
	- app-server
		- cmd (app-server folder): mvnw package
	- app-client
		- cmd (app-client folder): ng build --prod

4. Deploy Application
	- Copy to file into production environment
		- [Production Root Folder]/
			- rest-service-cors-0.0.1-SNAPSHOT.jar
			- public/
				- index.html
				- favicon.ico
				- main.xxxxxxx.js
				- polyfills.xxxxxxx.js
				- runtime.xxxxxxx.js
				- styles.xxxxxxx.css

5. Start Application
	- cmd (production root folder): java -cp . -jar app-server-0.0.1-SNAPSHOT.jar  --spring.profiles.active=prod
	- browse to : http://[production ip or domain]:8080/
