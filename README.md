# BeautyServices

The **BeautyServices** project is a web application created using Spring Boot, designed to manage cosmetic services.


## Description

The BeautyServices application is designed to provide an easy and intuitive interface for managing various aspects of beauty services. It allows users to browse available services, manage bookings, and access detailed information about each cosmeticService.


## Videos
### Presentation of the application's functionality from the user's perspective.

https://github.com/PiotrBa/BeautyServices/assets/92756702/b6fcff57-a91e-45ad-ae70-c6525c6582ae

### Presentation of the application's functionality from the admin's perspective.
- Part.1
Adding a service and a user, as well as their editing.

https://github.com/PiotrBa/BeautyServices/assets/92756702/1bfb9807-a054-47c3-aa6d-ad13129711f4

- Part.2
Adding a new admin, booking, editing, and deleting an appointment, all without any issues related to the foreign key constraint in the database.

https://github.com/PiotrBa/BeautyServices/assets/92756702/f54dbf7e-7349-4c4a-9558-d370bf2a0857


## User data
### The admin does not have access to users' passwords, and each password is encrypted with "bcrypt".
(Jest to zdjecje zanim uzytkownik Julia Smith zostala usunieta).

![users_list](https://github.com/PiotrBa/BeautyServices/assets/92756702/0e47b162-ae3f-4d96-9af8-14aaff4dd622)


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
