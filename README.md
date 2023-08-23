Project Description

FLIGHT search.
This api finds flight list based on origin and destination. 
It has additional feature to sort the result based price and duration or other attributes in ascending/descending order.

Parameters:
This API returns a list of flights based on input parameters to the endpoint /flights

Request parameters to be provided: Required - String flightOrigin 
                                   Required - String flightDestination
                                Optional - String sortDirection (ASC/DESC)
                                Optional - String sortBy(price) 

Prerequisite To Run
For building and running the application you need: * [JDK 8] or higher * [Maven 3.x] * Spring 3 or Higher

Procedure to Run :
This spring boot application can be run directly from the Spring Boot main class
This spring boot application can run independent of IDE using below approaches:
1) Using Maven: 
    -Navigate to the root of the project via command line and execute the command: mvn spring-boot:run
2) Using the java -jar command:
   - change the directory to the current project directory and run the following command in cmd: 
   $ java -jar target/api-0.0.1-SNAPSHOT.jar

Clone this repository

Project-> Maven Update

Build the project using maven:	mvn clean install
Once successfully built, it can be run using Run as Spring boot Application directly from IDE

END POINTS
To see the data in H2 database: http://localhost:8080/h2/login.jsp , user:sa .Test Connection and Connect.

The service is exposed to : http://localhost:8080/flights/v1

To test the API ,We need to pass the required flightOrigin, flightDestination and optional sortBy field name or optional sortDirection.

Following is a sample URL which can be run on Postman to see the response.
http://localhost:8080/flights/v1?src=BOM&dest=DEL&sortBy=duration&sortDir=desc

Swagger can be used at below url after starting the application:
http://localhost:8080/swagger-ui/index.html#/flight-controller/getFlights

Actuator Endpoints for the application Can be seen at below Url:
http://localhost:8080/actuator/

Test Case Implementation
Test cases for Controller, Service and Repository are in test packages.

License
None.
