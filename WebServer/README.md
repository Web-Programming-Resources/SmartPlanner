# SmartPlanner - Server

This is a *Spring Boot* project created via [Spring Initializr](https://start.spring.io). The server incorporates multitier architecture (repository, service, controllers) and the communication is present via **REST API**. Objects sent/received from the client are all *DTOs*. Entities are mapped to the database using **Spring Data JPA**.

As far as security is concerned **JSON Web Tokens** are utilized. Moreover, each endpoint is secured using the **@PreAuthorize** annotation and the permission is granted based on the required user role. The **SecurityContext** is employed to ensure that each user cannot access resources other than his own. The passwords are encrypted using *BCryptPasswordEncoder*.

The concurrency is applied in the *getOptimalPlan()* method. The first thread uses the **Future** interface and another one employs the **Callable** interface. As for the exceptions, the *InvalidDataProvidedException* indicates, as the name suggests, invalid data provided to the SmartPlanner. The client receives this exception whenever it is thrown.

**MySQL** is being used as a database management system.

## External Libraries / Tools

* [Lombok](https://projectlombok.org) - Automates the most-repetitive, boilerplate code. Usage via annotations
* [ModelMapper](http://modelmapper.org) - An intelligent, refactoring safe object mapping library that automatically maps objects to each other
* [Swagger](https://swagger.io) - API developer tool used to demonstrate the usage of various endpoints
* [Logger (SLF4J)](https://www.slf4j.org) - Simple Logging Facade for Java
* [JSON Web Tokens](https://jwt.io) - An open, industry standard RFC 7519 method for representing claims securely between two parties

## Launching the Server

**Make sure that [Maven](https://maven.apache.org) is installed on your machine**

* First of all, import this project into your IDE (only IntelliJ was tested). You can follow [this tutorial](https://www.lagomframework.com/documentation/1.4.x/java/IntellijMaven.htm)
* The next step is to build the application - any dependencies should be resolved automatically by maven (**network connection required**)
* Run the application

## Author

* [Kamil Nowak](https://github.com/nowakkamil)

##### *Developed on Windows 10 using IntelliJ IDEA*
