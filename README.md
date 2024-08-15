# IT Support Management System

## Overview

The IT Support Management System is a comprehensive web application designed for managing and assigning support tickets to technicians. Built with a modern tech stack, this system streamlines the process of issue reporting, assignment, and resolution in an IT support environment.

## Features

- **User Authentication & Authorization**
  - JWT-based secure authentication.
  - Role-based access control (Admin, Client, Technician).
- **Ticket Management**
  - Create, view, update, and delete support tickets.
  - Assign tickets to technicians using a popup interface.
  - Real-time status tracking with color-coded indicators.
- **Technician Assignment**
  - Admins can assign tickets to technicians from a user-friendly popup interface.
- **Responsive Design**
  - Mobile-first approach ensuring accessibility across all devices.
- **Interceptors & Guards**
  - HTTP Interceptor for attaching JWT tokens to API requests.
  - Route guards for securing access to different parts of the application.
- **MapStruct Integration**
  - DTO mapping for seamless data transfer between the frontend and backend.

## Technologies Used

### Frontend
- **Angular 17**: The latest version of Angular for building a dynamic and robust frontend.
- **NgRx**: For state management, handling global application state.
- **Bootstrap**: Ensuring a responsive and modern UI.
- **Boxicons**: A stylish icon library used across the application.
- **CSS Flexbox/Grid**: For designing flexible and efficient layouts.

### Backend
- **Spring Boot**: Framework for building a robust and scalable backend.
- **Spring Security**: Implementing security with JWT-based authentication.
- **Spring Data JPA**: For data persistence and interaction with MySQL.
- **Hibernate**: ORM framework for managing relational data.
- **MapStruct**: For mapping between Java objects and Data Transfer Objects (DTOs).
- **MySQL**: Database for storing application data.

## Setup Instructions

### Prerequisites

- **Node.js** and **npm**: Required for Angular CLI and package management.
- **Java 21**: Required to run the Spring Boot application.
- **MySQL**: Required to run the database.

## Usage

### Authentication

- **Login:** Users can log in using their credentials. JWT tokens are used to secure the sessions.
- **Role Management:** Admins have access to manage users and tickets. Technicians can view assigned tickets, and clients can report issues.

### Ticket Management

- **Admin Dashboard:** Admins can view all tickets, assign them to technicians, and manage user roles.
- **Client Interface:** Clients can report issues and view the status of their tickets.
- **Technician Interface:** Technicians can view tickets assigned to them and update their status.

### Interceptors & Guards

- **JWT Interceptor:** Automatically attaches JWT tokens to outgoing HTTP requests.
- **Auth Guards:** Protect routes from unauthorized access based on user roles.

### Data Mapping with MapStruct

- **DTO Mapping:** MapStruct is used to convert between entities and DTOs, ensuring efficient data transfer between layers.



### Explanation:

1. **Overview:** Provides a brief description of the project, focusing on what the project does and its main features.
2. **Features:** Highlights the core functionalities and technologies integrated into the application, including JWT, interceptors, guards, and MapStruct.
3. **Technologies Used:** Lists the technologies used on both frontend and backend, emphasizing the use of Angular, Spring Boot, and MapStruct.
4. **Setup Instructions:** Provides step-by-step guidance on how to set up the project locally, including backend and frontend setup.
5. **Usage:** Describes how different parts of the application work, such as authentication, ticket management, interceptors, and guards.
6. **Project Structure:** Outlines the directory structure of the project for easy navigation.
7. **Contributing:** Encourages contributions with guidelines on how to fork the repo and submit changes.
8. **License:** Details the project's licensing information.
9. **Acknowledgements:** Credits the tools and libraries that made the project possible. 

This `README.md` gives a comprehensive guide to setting up, using, and contributing to your IT Support Management System, making it clear and user-friendly for developers and users alike.
