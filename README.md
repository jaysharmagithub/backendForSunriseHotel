# Sunrise Hotel Backend Management System


## Project Overview

The Sunrise Hotel Backend Management System is a comprehensive, scalable RESTful API designed to handle all aspects of hotel operations including room management, reservations, customer management,
and administrative functions. Built with modern backend technologies and following industry best practices.

## Key Features

- **Room Management**: Complete CRUD operations for hotel rooms, room types, and availability
- **Reservation System**: Advanced booking  with  availability checking
- **Customer Management**: Comprehensive guest profile management with booking history
- **Authentication & Authorization**: JWT-based secure authentication with role-based access control
- **Admin Dashboard**: Full administrative controls for hotel operations

##  Architecture & Technology Stack

### Backend Technologies
- **Runtime**: Java 17
- **Framework**: Spring boot 3.2.1
- **Database**: MySQL with Spring Data JPA (Hibernate ORM)
- **Authentication**: spring Security with JWT (io.jsonwebtoken)
- **Validation**: Hibernate Validator (spring-boot-starter-validation)
- **File Upload**: Mutlipart
- **Logging & Monitoring**: Spring boot Actuator
- **Testing**: Jest & Supertest
- **API Documentation**: Swagger/OpenAPI 3.0
- **Annotation Processing**: Lombok
- **Build Tool**: Maven
- **Envrionment-Config**: application.yml/application.properties

### DevOps & Infrastructure
- **Containerization**: Docker
- **Environment Management**: dotenv file
- **Security**: RBAC, CORS, Rate Limiting

### External Services
- **Payment Gateways**: Stripe, PayPal (update soon and intergeate soon)
- **Email Service**: SendGrid/AWS SES  (update soon and intergeate soon)
- **Cloud Storage**: AWS S3 (for images)  (update soon and intergeate soon)
- **Maps Integration**: Google Maps API  (update soon and intergeate soon)

## Database Schema


##  `user` Table

| Column Name | Data Type    | Nullable | Key  | Extra          | Description          |
|-------------|--------------|----------|------|----------------|----------------------|
| id          | BIGINT       | NO       | PRI  | auto_increment | Primary user ID      |
| first_name  | VARCHAR(255) | YES      |      |                | User's first name    |
| last_name   | VARCHAR(255) | YES      |      |                | User's last name     |
| email       | VARCHAR(255) | YES      |      |                | User's email address |
| password    | VARCHAR(255) | YES      |      |                | Hashed password      |

---

## `room` Table

| Column Name | Data Type      | Nullable | Key  | Extra          | Description            |
|-------------|----------------|----------|------|----------------|------------------------|
| id          | BIGINT         | NO       | PRI  | auto_increment | Unique room ID         |
| room_type   | VARCHAR(255)   | YES      |      |                | Type of room           |
| room_price  | DECIMAL(38,2)  | YES      |      |                | Price per night        |
| is_booked   | BIT(1)         | NO       |      |                | 1 = booked, 0 = free   |
| photo       | MEDIUMBLOB     | YES      |      |                | Photo of the room      |

---

## `booked_room` Table

| Column Name       | Data Type     | Nullable | Key  | Extra          | Description                      |
|-------------------|---------------|----------|------|----------------|----------------------------------|
| booking_id        | BIGINT        | NO       | PRI  | auto_increment | Unique booking ID                |
| guest_full_name   | VARCHAR(255)  | YES      |      |                | Name of the guest                |
| guest_email       | VARCHAR(255)  | YES      |      |                | Email of the guest               |
| room_id           | BIGINT        | YES      | MUL  |                | Foreign key to `room.id`         |
| check_in          | DATE          | YES      |      |                | Check-in date                    |
| check_out         | DATE          | YES      |      |                | Check-out date                   |
| adults            | INT           | YES      |      |                | Number of adults                 |
| children          | INT           | YES      |      |                | Number of children               |
| total_guest       | INT           | YES      |      |                | Total guests                     |
| confirmation_code | VARCHAR(255)  | YES      |      |                | Unique booking code (optional)   |

---

##  `role` Table

| Column Name | Data Type     | Nullable | Key  | Extra          | Description         |
|-------------|---------------|----------|------|----------------|---------------------|
| id          | BIGINT        | NO       | PRI  | auto_increment | Unique role ID      |
| name        | VARCHAR(255)  | YES      |      |                | Role name (e.g. admin, guest) |

---

##  `user_roles` Table

| Column Name | Data Type | Nullable | Key | Extra | Description                  |
|-------------|-----------|----------|-----|-------|------------------------------|
| user_id     | BIGINT    | NO       | MUL |       | Foreign key to `user.id`     |
| role_id     | BIGINT    | NO       | MUL |       | Foreign key to `role.id`     |

---


```

## 🔧 API Endpoints

### Authentication & User Management
```
POST   /api/auth/register-user                  # User registration
POST   /api/auth/login                          # User login
GET    /api/users/all                           # Get users
GET    /api/users/{email}                       # Get user details via email id 
DELETE /api/users/delete/{userId}               # delete user 
```

### Room Management
```
GET    /api/rooms/all-rooms                     # Get all rooms
GET    /api/rooms/room/:id                      # Get specific room
POST   /api/rooms/add/new-room                  # Create new room (Admin only)
PUT    /api/rooms/update/:id                    # Update room (Admin only)
DELETE /api/rooms/delete/room/:id               # Delete room (Admin only)
GET    /api/rooms/available-rooms               # Get all available rooms 
POST   /api/rooms/room/types                    # Create room types
```

### Reservation Management
```
GET    /api/bookings/all-bookings               # Get all reservations(Admin only)
GET    /api/bookings/confirmation/:code         # Get specific reservation(Admin Or Authorized user only)
POST   /api/bookings/room/:roomId/booking       # Create new reservation
DELETE /api/bookings/:id/delete                 # Cancel reservation
GET   /api/bookings/user/:email/bookings        # Get all bookings of user
```

###    Admin 
```
GET    /api/roles/all-roles                         # Get all roles
GET    /api/roles/create-new-role                   # Create a role
DELETE    /api/roles/delete/:id                     # delete a role
POST    /api/roles/remove-all-users-from-role/:id   # remvoe all user roles
POST    /api/roles/remove-user-from-role/:id        # remvoe   a user roles
POST    /api/roles/assign-user-to-role/:id          # remvoe   a user roles
```

##  Security Features

### Authentication & Authorization
- JWT-based stateless authentication
- Role-based access control (RBAC)
- Password hashing using bcrypt
- token mechanism


##  Installation & Setup


##  Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8+
- Git

## Local Development Setup

### 1. Clone the Repository
```bash
git clone https://github.com/jaysharmagithub/backendForSunriseHotel.git
cd backendForSunriseHotel
```

### 2. Configure Application Profiles
Use multiple profiles for different environments:
```bash
src/main/resources/
├── application.properties          # common config
├── application-dev.properties     # development
├── application-prod.properties    # production
```

Sample `application-dev.properties`:
```properties
spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.profiles.active=dev
```

> Use environment variables instead of hardcoding secrets.

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run with Profile
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

The backend server will start on `http://localhost:8080`

---

##  Docker Setup

### Docker Compose for MySQL
Create a `docker-compose.yml`:
```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8
    container_name: sunrise-mysql
    environment:
      MYSQL_ROOT_PASSWORD: ${DB_PASSWORD}
      MYSQL_DATABASE: ${DB_NAME}
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:
```

```bash
# Build and run with Docker Compose
docker-compose up --build

# Run in detached mode
docker-compose up -d
---

##  Secure Configuration
Use `spring.config.import` or `application-secrets.properties` (Git ignored).

```properties
# JWT Configuration
auth.token.expirationInMils=3600000
auth.token.jwtSecret=${JWT_SECRET}

# File Uploads
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

---

##  Testing

### Run Unit Tests
```bash
mvn test
```

### Run Integration Tests with Profile
```bash
mvn test -Dspring.profiles.active=test
```

---

##  API Documentation

### Swagger (SpringDoc)
Add in `pom.xml`:
```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.1.0</version>
</dependency>
```

Swagger UI:
- Dev: `http://localhost:8080/swagger-ui.html`

---

##  Observability

### Logs
```properties
logging.level.root=INFO
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### Health + Metrics (Spring Actuator)
Add to `pom.xml`:
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Enable endpoints:
```properties
management.endpoints.web.exposure.include=health,info,metrics
```
Access:
```http
GET http://localhost:8080/actuator/health
```

---

##  Database Migration

Use Flyway for schema versioning:
```xml
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-core</artifactId>
</dependency>
```
Place migration SQL files in:
```bash
src/main/resources/db/migration/V1__init.sql
```

---

##  CI/CD and Quality

- **Code Style**: Use Checkstyle plugin
- **Linting**: Maven Enforcer + formatting plugins
- **CI**: Add GitHub Actions workflow
- **Security**: Use OWASP dependency-check

---

##  Troubleshooting

### Port Already in Use
```bash
lsof -ti:8080 | xargs kill -9
```

### MySQL Connection
```sql
mysql -u root -p
USE test_hotel_db;
SHOW TABLES;
```

#### Docker Deployment
```bash
# Build production image
docker build -t sunrise-hotel-backend .

# Run with Docker Compose
docker-compose -f docker-compose.prod.yml up -d
```

#### Cloud Deployment
- **AWS**: Deploy using ECS, Lambda, or EC2
- **Azure**: Use Azure Container Instances or App Service
- **GCP**: Deploy on Cloud Run or Compute Engine
- **Heroku**: Simple deployment with Git push

##  Contributing

### Development Workflow
1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -m 'Add amazing feature'`
4. Push to branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

### Code Style Guidelines
- Follow ESLint configuration
- Use SonarCube 
- Write meaningful commit messages
- Include tests for new features
- Update documentation as needed

### Pull Request Process
1. Ensure all tests pass
2. Update documentation if needed
3. Add screenshots for UI changes
4. Request review from maintainers
5. Address feedback and merge

##  Roadmap

### Phase 1 (Current)
- [x] Basic CRUD operations
- [x] Authentication system
- [x] Room management
- [x] Reservation system
- [x] Customer Management

### Phase 2 (In Progress)
- [ ] Payment Gateway Integration
- [ ] Advanced reporting dashboard
- [ ] Mobile app API endpoints
- [ ] Real-time notifications
- [ ] Multi-language support
- [ ] Advanced search and filtering

### Phase 3 (Planned)
- [ ] Machine learning recommendations
- [ ] IoT device integration
- [ ] Advanced analytics
- [ ] Third-party integrations
- [ ] Microservices architecture
##  Support

### Getting Help
- **Issues**: [GitHub Issues](https://github.com/jaysharmagithub/backendForSunriseHotel/issues)
- **Discussions**: [GitHub Discussions](https://github.com/jaysharmagithub/backendForSunriseHotel/discussions)
- **Email**: 8313sharma.jay.sy@gmail.com

### Reporting Bugs
Please include:
- Environment details (Node.js version, OS)
- Steps to reproduce
- Expected vs actual behavior
- Error messages/logs
- Screenshots (if applicable)

##  License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

##  Acknowledgments

- Spring /Spring boot team for the excellent framework
- MySQL team for the robust database
- Open source community for various packages
- Friends/Family/Teachers who helped improve this project

---

##  Project Statistics

- **Total Lines of Code**: 10,000+
- **API Endpoints**: 20+
- **Database Collections**: 5
- **Dependencies**: 10+
- **Development Time**: 3 months

---

*Built with ❤️ by [Jay Sharma](https://github.com/jaysharmagithub)*

**Last Updated**: July 2025
