# Job Management System - Full-Stack Project

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Backend](#backend)
- [Frontend](#frontend)
- [Running Guide](#running-guide)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)

 ## Description
  The Full Stack Job Management System is a web-based application designed to help users efficiently manage job postings and track application progress. The system supports seamless CRUD features such as job creation, editing, deletion, and categorization. Being built with a full-stack architecture, the application demonstrates majestic interaction between frontend and backend components, database integration, and secure API handling.
  This project serves as a comprehensive example of how modern web applications are structured, and it's suitable for portfolio presentation or as a foundation for job-related SaaS products. Furthermore, as this project is fecilitously implemented with highly responsive designs, it is
  well-founded for Both mobile and desktop.

  ## Features
- Add, edit, view, and delete job records
- Responsive design
- Containerized app with Redis via Docker Compose.
- Fast in-memory key-value store for caching and optimized data retrieval.
- Efficient dependency and build management.

## Technologies Used
## Tech Stack

| Layer           | Technology                      |
|------------------|----------------------------------|
| Backend          | Java, Spring Boot,Spring REST API, JPA Hibernate|
| Database          | PostgreSQL                     |
| Frontend          | React, Tailwind                |
| Cache/Storage    | Redis                           |
| Containerization | Docker, Docker Compose          |
| Build Tool       | Maven                           |

---
### Backend

- **Spring REST API** - Framework for building the backend API
- **JPA Hibernate** - Persistence framework for interacting with the database
- **PostgreSQL** - Relational database

### Frontend

- **React** - JavaScript library for building user interfaces
- **React Router** - For navigation
- **Formik** - For form management and validation
- **Tailwind** - For responsive and captivating design

## Application Configuration
> [!NOTE]
> ```
> spring.application.name=softwareProjectWithDocker
> spring.datasource.url=jdbc:postgresql://localhost:5332/softwareProjet
> spring.datasource.username=postgres
> spring.datasource.password=neophenon!@#
> spring.datasource.driver-class-name=org.postgresql.Driver
> 
> // tranditional hibernate configurations
> spring.jpa.hibernate.ddl-auto=update
> spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
> spring.jpa.properties.hibernate.format_sql=true
> spring.jpa.show-sql=true
> // redis configuration
> spring.cache.type=redis
> spring.jpa.show-sql=true
> spring.redis.host=localhost
> spring.redis.port=6379


### Contributing

## Fork the Repository:
**We welcome contributions to enhance the project! Follow these steps to contribute:
Click the "Fork" button at the top right of the repository page.**

## Create a New Branch:
```git checkout -b feature/your-feature```

## Make Your Changes:
Implement your feature or bug fix.

## Commit Your Changes:
```git commit -m "Add feature: your feature description"```

## Push To The Branch
```git push origin feature/your-feature```

## Create a Pull Request:
Open a pull request on GitHub and describe your changes.

### Acknowledgements
🙏
Special thanks to the open-source community for the tools and libraries used in this project
