# e-Moodle-platform-with-microservices-and-REST-APIs

This project replicates the core functionalities of an e-learning platform by implementing a microservices-based architecture deployed in a Docker cloud environment. Each microservice is responsible for a specific domain—such as students, teachers, or educational materials—and interacts with its own dedicated data store, using either SQL or MongoDB depending on the nature of the data.

All operations are exposed via RESTful APIs that follow strictly the HTTP standards and REST principles, including proper use of HTTP methods and status codes. This modular design ensures scalability, maintainability, and ease of integration with other services or front-end applications.

Technologies used: Spring Boot (Java) for backend microservices, ReactJS for the front-end interface, Docker for containerization and deployment, and both SQL and MongoDB for data persistence.
