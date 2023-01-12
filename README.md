# vote
SpringBoot Vote system.

## How to run
### The best way to run the application in order to test the main features is:
Firs things first, you need to have a MySQL database running on your machine. to make it easier to test just run THIS .sql script to populate the database with some data. ()

1. Run the application using the main class in your IDE (SpringBootVoteApplication.java).
2. Open Postman and using the Runner tool you can run the requests that are in the collection "SpringBootVote.postman_collection.json" in the root of the project. (You can also import the collection in your Postman and run the requests manually.)
3. In Order to make a good use of all Postman features, we just use Postman tests and pre-scrips to automate the votes, it is not necessary to run the requests manually.

### Dependencies

This project uses the following dependencies:
- Spring Boot 2.6.13
- Spring Data JPA for database access
- Spring Validation for data validation
- Spring Web for building web applications
- MySQL Connector for connecting to a MySQL database
- Spring Boot Test for writing tests

### Configuration

- The project uses the MySQL Connector for connecting to a MySQL database. You will need to provide the correct database configuration in the application.properties file or via environment variables.
- The groupId is `com.api`, and artifactId is `vote`, and version is `0.0.1-SNAPSHOT`