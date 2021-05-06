# jasper-report
This is a sample to demonstrate the integration of [jasper report](https://community.jaspersoft.com/project/jaspersoft-studio) into the project. This sample include: -
- Generate jasper report in pdf format.
- Create RESTful APIs in spring boot project for providing pdf report as byte array to client.
- Create Angular component for displaying pdf (byte array) from the RESTful APIs by using [ng2-pdf-viewer](https://www.npmjs.com/package/ng2-pdf-viewer.


## Instruction to create this sample project

#### 1. Prerequisite
- Checkout [spring-data](https://github.com/kritdev/spring-boot-with-angular/tree/spring-data) branch.
- Install MySQL Database. And create an empty database (default name is sample). (The database configuration can be changed in app-server/src/main/resources/application.properties).

#### 2. app-server
- Update pom.xml. Adding dependency as below: -
```
 	  	<dependency>
 	    	<groupId>net.sf.jasperreports</groupId>
 	    	<artifactId>jasperreports</artifactId>
 	    	<version>6.16.0</version>
 		  </dependency>
```
- Create jasper report file. [sample_report.jrxml](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-server/src/main/resources/reports/sample_report.jrxml).
- Create [ReportResource.java](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-server/src/main/java/com/example/appserver/rest/ReportResource.java) for providing pdf report as byte array in GET method.

#### 3. app-client 
- Install ng2-pdf-viewer in Angular project. By running this command in command line (in app-client folder).
```
	npm install ng2-pdf-viewer --save
```
- Create [report.service.ts](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/report.service.ts) for retrieving pdf byte array from app-server. In order to read the byte array data, we need to add httpOptions as
```
'responseType'  : 'arraybuffer' as 'json'
```
- Create Pdf component ([pdf.component.ts](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/pdf/pdf.component.ts), [pdf.component.html](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/pdf/pdf.component.html), [pdf.component.css](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/pdf/pdf.component.css)) for calling report.service.ts and displaying pdf in browser
Note : to display pdf, binging pdf byte array data with 'src' property of pdf-viewer, ng2-pdf-viewer's component.

- Update [app.module.ts](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/app.module.ts) by importing PdfViewerModule of ng2-pdf-viewer.
- Update [app.component.ts](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/app.component.ts) and [app.component.html](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/app.component.html) for displaying pdf report.
Note : using @viewChild for refreshing report data.
