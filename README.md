# Course Selection Application

## Overview

This project is a web application that allows users to select courses based on various criteria, including course mode, course lecturer, and course category. The application features a React frontend that communicates with a Spring Boot backend to provide a seamless user experience.

## Tech Stack

- **Frontend**: 
  - **React**: A JavaScript library for building user interfaces, allowing for the development of single-page applications with a component-based architecture.
  
- **Backend**: 
  - **Spring Boot**: A Java-based framework that simplifies the development of stand-alone, production-ready Spring applications. It provides built-in features for RESTful API creation, configuration management, and dependency injection.
  
- **Database**: 
  - **MySQL**: A widely-used relational database management system (RDBMS) that stores application data in structured formats, enabling efficient data retrieval and management.
  
- **API**: 
  - **RESTful Services**: A design pattern for web services that allows clients to interact with server resources using standard HTTP methods (GET, POST, PUT, DELETE).
  
- **Testing**: 
  - **Swagger**: A tool for documenting RESTful APIs, providing an interactive interface for testing endpoints and viewing API specifications.
  - **Postman**: A collaborative platform for API development that allows for testing, documenting, and sharing APIs.

- **Document Generation**:
  - **iText**: A library for creating and manipulating PDF documents programmatically.
  - **OpenPDF**: An open-source PDF library used for generating and editing PDF files.

  ## Screenshots

Here are some screenshots of the Course Selection Application:

![Course Selection Page](C:\Users\venuv\Pictures\Screenshots\Screenshot 2024-09-17 144050.png)

## Features

- **Course Filtering**: Users can filter courses based on:
  - Course Mode (e.g., online, offline)
  - Course Lecturer
  - Course Category (e.g., science, arts, technology)
  
- **Course Management**: Users can edit and delete courses from the filtering options.

- **Document Generation**: Users can generate PDF documents and Excel sheets based on selected filters, enabling easy export of course information.

- **Responsive Design**: The application is built with a responsive layout to ensure usability across different devices.

## Architecture

- **Frontend**: Developed using React, the application offers a dynamic and interactive user interface. Key features include component reusability and state management using React Hooks.
  
- **Backend**: Built with Spring Boot, the backend handles RESTful API requests and manages course data. It includes features like Spring Data JPA for database interactions, Spring Security for securing the API, and iText/OpenPDF for document generation.

- **Database**: MySQL is used to store course information, providing reliable data management and query capabilities.

## Getting Started

### Prerequisites

- Node.js (v14 or higher)
- Java (v11 or higher)
- Maven
- MySQL

### Installation

1. **Clone the repository**:
   ```bash
   git clone [<repository-url>](https://github.com/VenuGopalPattem1/ReportGeneration.git)
   cd <repository-directory>

