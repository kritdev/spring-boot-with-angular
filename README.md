# jasper-report
This is a sample to demonstrate the integration of [jasper report](https://community.jaspersoft.com/project/jaspersoft-studio) into the project. This sample include: -
- Generate jasper report in pdf format.
- Create RESTful APIs in spring boot project for providing pdf report as byte array to client.
- Create Angular component for displaying pdf (byte array) from the RESTful APIs by using [ng2-pdf-viewer](https://www.npmjs.com/package/ng2-pdf-viewer).


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
- Create jasper report file [sample_report.jrxml](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-server/src/main/resources/reports/sample_report.jrxml).<br>
Note : this report file create by using jaspersoft studio. [Tutorial for creating report in jaspersoft studio.](https://medium.com/@maeluenie/jasper-report-with-spring-boot-service-b896456ec856)
- Create [ReportResource.java](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-server/src/main/java/com/example/appserver/rest/ReportResource.java) for providing pdf report as byte array in GET method.

#### 3. app-client 
- Install ng2-pdf-viewer in Angular project. By running this command in command line (in app-client folder).
```
	npm install ng2-pdf-viewer --save
```
- Create [report.service.ts](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/report.service.ts) for retrieving pdf byte array from app-server. In order to read the byte array data, we need to add httpOptions as :-
```
'responseType'  : 'arraybuffer' as 'json'
```
- Create Pdf component ([pdf.component.ts](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/pdf/pdf.component.ts), [pdf.component.html](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/pdf/pdf.component.html), [pdf.component.css](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/pdf/pdf.component.css)) for calling report.service.ts and displaying pdf in browser.<br>
Note : to display pdf, binging pdf byte array data with 'src' property of pdf-viewer, ng2-pdf-viewer's component.

- Update [app.module.ts](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/app.module.ts) for importing PdfViewerModule of ng2-pdf-viewer.
- Update [app.component.ts](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/app.component.ts) and [app.component.html](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report/app-client/src/app/app.component.html) for displaying pdf report.<br>
Note : using @viewChild for refreshing report data.

<br><br>
## option 1: for filling Jasper Report with collection instead of JDBC Connection
- Jasper Report File: [sample_report.jrxml](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report-with-collection/app-server/src/main/resources/reports/sample_report.jrxml), in each fields, add property name for javabean.
```
    <property name="net.sf.jasperreports.javabean.field.property" value="id"/>
```
- app-server: [ReportResource.java](https://github.com/kritdev/spring-boot-with-angular/blob/jasper-report-with-collection/app-server/src/main/java/com/example/appserver/rest/ReportResource.java), using JRBeanCollectionDataSource as report data source.
```
    List<DataMessage> dataList = dataMessageRepository.findAll();
    JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataList);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
```
Please visit this [branch](https://github.com/kritdev/spring-boot-with-angular/tree/jasper-report-with-collection) for the sample code.

<br><br>
## option 2: for displaying pdf in Angular
For a full-blown PDF viewer in Angular, we can use [ngx-extended-pdf-viewer](https://pdfviewer.net/extended-pdf-viewer/getting-started). the steps are:-
- Install ngx-extended-pdf-viewer.
```
	npm install ngx-extended-pdf-viewer --save
```
- Update Angular.json.
```
        "assets": [
          "src/favicon.ico",
          "src/assets",
          {
            "glob": "**/*",
            "input": "node_modules/ngx-extended-pdf-viewer/assets/",
            "output": "/assets/"
          }
        ],
```
- Update app.module.ts to import NgxExtendedPdfViewerModule.
```
	import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';
```
- Update pdf.component.html.
```
	<ngx-extended-pdf-viewer [src]="pdfSource" useBrowserLocale="true"></ngx-extended-pdf-viewer>
```
Please visit this [branch](https://github.com/kritdev/spring-boot-with-angular/tree/ngx-extended-pdf-viewer) for the sample code.

<br><br>
## option 3: for using Font Extension with Jasper Report
For using Font that is not available to the JVM, we could follow these steps:
- In jrxml file, set fontName property.
```
	<textElement>
		<font fontName="Angsana New" size="22"/>
	</textElement>
```
- Supply the Font in pom.xml
  - Create and export the Font Extension by following instructions in this link : [Custom Font with the Font Extension](https://community.jaspersoft.com/wiki/custom-font-font-extension).
  - Include the exported Font Extension in our project, by adding this section into the pom.xml file. For more information, please visit [stackoverflow : how-to-add-local-jar-files-to-a-maven-project](https://stackoverflow.com/questions/4955635/how-to-add-local-jar-files-to-a-maven-project?rq=1).
```
	<dependency>
		<groupId>font-AngsanaNew</groupId>
		<artifactId>font-AngsanaNew</artifactId>
		<version>1.0</version>
		<scope>system</scope>
		<systemPath>${project.basedir}/src/main/resources/reports/font-AngsanaNew.jar</systemPath>
 	</dependency>
```
note: In the example project, we will use Angsana New font (Thai Font) in Jasper report file.
Please visit this [branch](https://github.com/kritdev/spring-boot-with-angular/tree/jasper-report-with-collection-thaiFont) for the sample code.
