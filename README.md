# Gestión de Inventario
This project is an inventory management application. It provides CRUD operations to manage inventory items and sales. The application uses a MySQL database to store information.

## Technologies Used
- Java 17.
- SpringBoot 3.2.0.
- JavaScript.
- HTML.
- CSS.
- jQuery.
- MySQL.
- Apache NetBeans IDE 20.

## Quick Access
gestioninventario.up.railway.app

## Installation
1. Clone the repository or download the source code.
2. The database script is in the inventario.sql file at the root of the project.
3. Update the connection settings in the application.properties file.

```properties
# src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/inventario
spring.datasource.username=your_username
spring.datasource.password=your_password
```
4. You can access the interface through http://localhost:8080 or the port of your choice specified in the application.properties file.
