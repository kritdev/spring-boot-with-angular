# jasper-report
This is a sample to demonstrate how to use data collection in Jasper Report


#### Create Jasper Report File with data adaptor
- [Working with a Collection of JavaBeans Data Adapter](https://community.jaspersoft.com/documentation/tibco-jaspersoft-studio-user-guide/v62/working-collection-javabeans-data-adapter)
- [StackOverflow : How to use Collection of Java Beans in data adapter](https://stackoverflow.com/questions/40902602/jaspersoft-studio-how-to-use-collection-of-java-beans-in-data-adapter)

  Summary
  - Create Adapter Bean as [sample-data-adapter folder](https://github.com/kritdev/spring-boot-with-angular/tree/jasper-report-with-collection/sample-data-adapter)
  - Build the sample-data-adapter as jar file.
  - In Jaspersoft Studio, Repository Explorer, Right click on 'Data Adaptor'. Then select 'Create Data Adaptor'.
    - Select 'Collection of JavaBeans'.
    - Enter the information and click 'Finish'.
  - In Jaspersoft Studio, Repository Explorer, Rick click on the project. The select 'Build Path', and 'Configue Build Path...'.
    - Click 'Add External JARs...', and select sample-data-adapter jar file.
  - In Jaspersoft Studio, Repository Explorer, Open our report file.
    - Click Icon of 'DataSet and Query editor dialog'.
    - Select our just created adaptor.
    - Select 'Java Bean' Tab.
    - In Class Name, Click '...' button to search for the data class in our bean. Then our data field will be listed.
    - Select fields, and click 'Add selected field(s)' button for adding them to our report.
