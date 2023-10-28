# BeautyServices

The **BeautyServices** project is a web application created using Spring Boot, designed to manage cosmetic services.


## Description

The BeautyServices application is designed to provide an easy and intuitive interface for managing various aspects of beauty services. It allows users to browse available services, manage bookings, and access detailed information about each cosmeticService.


## Technical Details

- **Java Version**: 11

### Frameworks & Libraries:
- **Spring Boot**: 2.7.15
    - **Spring Data JPA**: (version managed by Spring Boot 2.7.15)
    - **Spring Validation**: (version managed by Spring Boot 2.7.15)
    - **Spring Web MVC**: (version managed by Spring Boot 2.7.15)
    - **Spring Security**: (version managed by Spring Boot 2.7.15)
- **Hibernate**: (version managed by Spring Boot 2.7.15)
- **MySQL Connector/J**: (runtime version)
- **Lombok**: 1.18.28 (optional)
- **JUnit**: (version managed by Spring Boot 2.7.15, scope: test)
- **JSTL**: 1.2
- **Tomcat Embed Jasper**: (version managed by Spring Boot 2.7.15)
- **Spring Security Taglibs**: (version managed by Spring Boot 2.7.15)


## How to Run the Application

To run the BeautyServices application on your local environment, follow these steps:

1. Clone this repository to your computer.
2. Install MySQL and create a database.
3. Configure the database connection in the `application.properties` file.
4. Run the application using the command `./mvnw spring-boot:run`.
5. The application will be accessible at: http://localhost:8080/homepage
6. Register and place an order :)

The administrator is always in the system with the login: adam and password: 1234.

After logging in, the application will recognize whether the user is a USER role or an ADMIN role and will redirect to the appropriate page.
