# vote
SpringBoot Vote system

(ainda estou escrevendo... e o projeto ainda não está pronto)

# Como rodar
É só abrir o projeto alguma IDE e sincronizar as dependencias do Maven, depois é só rodar a classe principal do projeto.

# How to run
## The best way to run the application in order to test its main features is:

### Firs things first, you need to have a MySQL database running on your machine. to make it easier to test just run THIS .sql script to populate the database with some data. ()

1. Run the application using the main class in your IDE (SpringBootVoteApplication.java).
2. Open Postman and using the Runner tool you can run the requests that are in the collection "SpringBootVote.postman_collection.json" in the root of the project. (You can also import the collection in your Postman and run the requests manually.)
3. In Order to make a good use of all Postman features, we just use Postman tests and pre-scrips to automate the votes, it is not necessary to run the requests manually.