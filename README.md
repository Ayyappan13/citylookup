# Explore City

## Introduction
The City Lookup application is a resource for building RESTful HTTP+JSON API to check if two cities have been connected or not.
The below API will check the City details in "city.txt" file and based on the request, it will respond either "yes" or "no"

If two cities have been mapped in the "city.txt" file, then it will return "yes", otherwise it will return "no"

  - http://localhost:8080/connected
  
  | City 1 | City 2 | RESPONSE |
  | ------ | ------ | ------   |
  | Boston | New York |  yes   |
  | Philadelphia | Newark |  yes   |
  | Newark | Boston |  yes   |
  | Trenton | Albany |  yes   |
  | Chicago | Florida |  no   |
  | Newark | Chicago |  no   |
  
## Steps
 - Download this application from below GitHug link and import as Maven project.
   - <GitHub>
 - After successfully imported, run the application.
 - city.txt file is available in "resource folder". 
    -  /resources/config/city.txt
  - Test application using REST client(Postman) by referring URI's below

## Links
URI's to check.

  - http://localhost:8080/connected?origin={city1}&destination={city2}
     - {city1} -> Newark
     - {city2} -> Boston

Swagger Spec can be explored here,
   - http://localhost:8080/v2/api-docs
  - http://localhost:8080/swagger-ui.html

Logs  
The log messages have customized in below format. The logs are in JSON format with below set of attributes. This will help us to easily understand the logs messages and help us to quickly trouble shoot and easy to track. This format will also help us more readable in monitoring tools like Kibana.
It will be logged in Console(local) and log file as well.
   - ../logs/CityLookUp.log
      - "timestamp" : "2020-08-11 03:42:23.500"
      - "level" : "info"
      - "Service" : "CityLookUp"
      - "TraceId" : "3e0294a632d3503d" (Sleuth Id)                  
      - "x-api-id" : "1234567890" (which will come from API gateway)
      - "Resource" : "URI's" (/connect)
      - "Cities" : "Boston - NewYork"
      - "Class" : ""Name of the class is being invoked.
      - "message" : ""to be printed from log.info()

Refer "logback-spring.xml" in "resources" for more details
      
Docker
   - Docker file is available in below path 
      - ../dockerfile
      
## Misc 
## connect with DataBase
With my own interest, I developed one more API which connects with Inbuild Database(H2) to check if two cities have been connected or not.
Below are the details of API which connect with Inbuild DataBase instead of City.txt file                    
               
  - http://localhost:8080/connectedFromDB/
  
## Links
URI's to check. This link will behave same as "city.txt"

  - http://localhost:8080/connectedFromDB?origin={city1}&destination={city2}
     - {city1} -> Newark
     - {city2} -> Boston
     
SCRIPT - data.sql
The data.sql will be executed during start up of the application. This script will load the data of City into H2 Inbuild DataBase.

H2 Console - Inbuild Database
Below are the details of H2DB console where we can check the details of City.  
   - http://localhost:8080/h2-console
      - Driver Class : org.h2.Driver
      - JDBC URL : jdbc:h2:mem:testdb      
      - User Name : sa
      - Password :
   - Click on "Connect"
   - Table Name :  EXPLORE_CITY_DETAILS
   - Click on "EXPLORE_CITY_DETAILS" table on the left

Technical Stack:
   - SpringBoot 2.3.2
   - REST API
   - Java 8
   - Spock & Groovy for Unit Testing
   - Swagger2
   - H2 database
   - logstash
   - Docker
   
            
 