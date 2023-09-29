# Divina Sopa - *Noxus*
![banner](readme/img/banner.png)

Welcome to the Divina Sopa project repository, a comprehensive solution for managing orders at "Divina Sopa" soup shop. This repository is a part of a microservices solution.

## Architecture Overview

Our project is built on a robust microservices architecture, leveraging the power of Spring Boot to provide flexible and efficient order management. Here's a glimpse of the technologies I've used:
![architecture](readme/img/architecture.png)

### Spring Boot
Spring Boot for Java development. It empowers me to create reliable and scalable services for your ordering needs.

### Docker
Docker plays a pivotal role in our project. It helps me manage PostgreSQL within a container, ensuring smooth database operations.

### Spring JPA
For simplified data transactions, I use Spring JPA. It simplifies database interaction, making data management much easier.

### Spring Web
The REST API, crucial for seamless communication with the database, is built with Spring Web. It enables clients to effortlessly place and track their orders.

### Amazon Web Services (AWS)
I've integrated AWS services into our project for enhanced functionality and reliability:

#### Amazon Simple Queue Service (SQS)
SQS is our go-to solution for managing queues. It enables asynchronous communication between microservices, ensuring efficient order processing.

#### AWS Lambda
To automate processes triggered by Amazon queues, I've employed AWS Lambda functions. This helps us keep our system responsive and efficient.

## Get Started

Follow the setup instructions below to get started with our project:

1. Clone this repository to your local machine.

2. Install Docker.

3. Run the Docker container to host the PostgreSQL database locally.

4. Run `package -f pom.xml` to create the .jar file. 

5. Run `docker build -t noxus-docker.jar .` to create the application image.

6. Run `docker-compose up` to up the container. 

We hope you have a delightful experience exploring and using our Divina Sopa project. If you have any questions or need assistance, please don't hesitate to reach out.

**Happy ordering!**

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)
