### java-spring-demo 
[Travis CI](https://travis-ci.org) Build Status: [![Build Status](https://travis-ci.org/jsicree/java-spring-demo.svg)](https://travis-ci.org/jsicree/java-spring-demo)

This repository contains a demo web application based on the Spring Framework. The purpose of this demo is to provide a working web application that highlights a number of commonly used tools and technologies including:
* Spring Core
* Spring Data JPA
* Spring MVC
* Form and Bean Validation via JSR-303 and Spring custom validators
* Content negotiation strategies to produce XML and JSON output
* Front End: HTML5, CSS3, JQuery and JQuery UI
* Unit Testing: JUnit
* Build: Maven 
* Database: Oracle *(Oracle Express)*
* Continuous Integration: Travis CI

The application consists of 2 projects named *springapp* and *springweb*. The *springapp* project contains the business and data access services for a simple domain. The *springweb* project is a simple web application containing a number of examples that use the services provided in *springapp*. The *springall* project is a parent project for both *springapp* and *springweb*. The *database* directory contains PL/SQL scripts that can be used to create and populate an Oracle database.




