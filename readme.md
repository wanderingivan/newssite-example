# newssite-example

 A basic news site example built using Struts 2 ,Spring, Tiles,Hibernate and MySql.
 
 
 
 How to run:
  Download repository
  Run /src/scripts/sql/createDatabase.sql on a database of your choice
  (Optional) Run /src/scripts/sql/createPresetDb.sql -adds pre made articles,users and comments
  Update src/main/resources/application.properties to include db url and user 
  Update application.properties to include default image folder
  (Optional) use image folder included at /src/images - application.properties expects absolute path
  Execute mvn tomcat7:run
  
 For integration tests an empty database must be created with createDatabase.sql.
 Test datasource configuration will be in com/newssite/test/configuration/DBUnitTestConfig.class   
  
Original theme: http://wpfreeware.com/preview/magexpress/