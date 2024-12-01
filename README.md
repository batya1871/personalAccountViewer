# Personal Account Viewer

## Description

**Personal Account Viewer** is a web application built with Spring Boot 3, designed for managing and viewing personal account data. The application supports user authentication, registration, and retrieving data from a PostgreSQL database.

## Technologies Used

- **Spring Boot 3**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **Thymeleaf** (for templates)
- **Lombok** (to reduce boilerplate code)
- **BCrypt** (for password hashing)

## Main Features

- **User Authentication**: Supports login and registration with role-based access control.
- **Account Search**: Allows searching personal account data by account number.
- **Data Retrieval**: The app fetches data about personal accounts from the database, including account details, devices, and transaction history.
- **Custom UserDetailsService**: A custom service for loading user data during authentication.

## Key Components

### Controllers:
- **AccountsController**: Manages account-related actions, including account search and displaying data.
- **UsersController**: Handles user login, registration, and authentication process.

### Services:
- **CustomUserDetailsService**: A custom service for user authentication.
- **UserService**: Manages user creation and validation.

### Data Access:
- **AccountDao**: Handles database queries to retrieve account data.
- **UserRepository**: Provides access to user data in the database.

### Security Configuration:
- **SecurityConfig**: Configures web application security, including login, registration, and password hashing using BCrypt.

## Project Structure

### Main Files and Directories:

- **PersonalAccountViewerApplication.java** — the main class that runs the Spring Boot application.
- **Controller**:
  - `AccountsController.java`
  - `UsersController.java`
- **Models**:
  - `Account.java`
  - `User.java`
- **Repositories**:
  - `UserRepository.java`
- **Services**:
  - `CustomUserDetailsService.java`
  - `UserService.java`
- **DAO**:
  - `AccountDao.java`
- **Security**:
  - `SecurityConfig.java`
  
### Database

The application connects to a PostgreSQL database where user and personal account data are stored. The main queries are executed to retrieve information related to accounts, such as connected devices and transaction values.

## Running the Application

1. **Clone the repository:**
   git clone https://github.com/yourusername/personalAccountViewer.git
2. Configure database connection parameters: Edit the application.properties or application.yml file in the src/main/resources directory to specify the correct database connection settings.
3. Run the application: Use Maven to run the application:
   mvn spring-boot:run
4. The application will be available at http://localhost:8080.


## Notes
- User authentication uses USER and ADMIN roles.
- BCrypt is used for password hashing.

Developed by Escapist_1871
