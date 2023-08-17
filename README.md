# FlightAPI

Project Description
FLIGHT search
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

Clone this repository

Project-> Maven Update

Build the project using maven:	mvn clean install
Once successfully built, it can be run using Run as Spring boot Application directly from IDE

END POINTS
To see the data in H2 database: http://localhost:8080/h2/login.jsp , user:sa .Test Connection and Connect.

The service is exposed to : http://localhost:8080/flights

To test the API ,We need to pass the required flightOrigin, flightDestination and optional sortBy field name or optional sortDirection.

Following is a sample URL which can be run on Postman to see the response.
http://localhost:8080/flights?src=BOM&dest=DEL

Test Case Implementation
Test cases for Controller, Service and Repository are in test packages.

License
None.
