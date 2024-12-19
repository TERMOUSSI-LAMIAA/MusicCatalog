REST API for Managing a Music Catalog

A company wants to implement a REST API to manage its music catalog. The API should allow for managing albums and songs with different access levels (USER/ADMIN) through secure, stateless authentication.
Main Entities
    
    Album
    Song
    User
    Role
Features
    
    Album Management
    Song Management
    User Management
    Security
       - Stateless authentication with JWT.
      - Role-based access control on each endpoint.
      - /api/admin/* requires the ADMIN role.
       - /api/user/* requires the USER role.

Password encryption using BCryptPasswordEncoder or similar.
JWT tokens
Tools and Technologies
    
    Framework: Spring Boot
    Database: MongoDB
    Security: Spring Security with JWT
    Build: Maven
    Testing: JUnit, Mockito
    Deployment: Docker, Jenkins
    API Documentation: Swagger
Author and Contact Information
   
    Termoussi Lamiaa 
    Email: lamiaa3105@gmail.com
