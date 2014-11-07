## Author
mohamed.elgabbouch@gmail.com

## Requirements

    * Java SDK 1.7
    * Maven 2
    
## Architecture

   1. The project is split into different layers :
   
       * view package: for presentation and display
       * model package: for model pojos
       * service package: for business logic
       * controller package: to commands for the model and view  
       
       
   2. Logging : 
       * slf4j api - logback (instead of log4j for better performance)
   

## How to build
    mvn clean install

## How to run unit testing
    mvn test
  
## How to execute the application as standalone jar
java -jar GoEuroTest.jar "STRING"


    
    
    