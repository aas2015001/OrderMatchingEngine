#This is a java based multi-threaded order matching application.

###Technology Stack
* Java 1.8
* Spring Boot
* Restful Web Service

###How to use the application
* Clone the repository
* Open a terminal and go to the project directory
* Package the project
``` 
    mvn -clean package
```
* Run the applicatoin
``` 
    java -jar target/om-restful-service-0.0.1-SNAPSHOT.jar
```


###Test scenarios
```
curl localhost:8080/book
curl -H "Content-Type: application/json" -X POST http://localhost:8080/sell -d "{\"qty\":10, \"prc\":15}"
curl -H "Content-Type: application/json" -X POST http://localhost:8080/sell -d "{\"qty\":10, \"prc\":13}"
curl -H "Content-Type: application/json" -X POST http://localhost:8080/buy -d "{\"qty\":10, \"prc\":7}"
curl -H "Content-Type: application/json" -X POST http://localhost:8080/buy -d "{\"qty\":10, \"prc\":9.5}"
curl localhost:8080/book
curl -H "Content-Type: application/json" -X POST http://localhost:8080/sell -d "{\"qty\":5, \"prc\":9.5}"
curl localhost:8080/book
curl -H "Content-Type: application/json" -X POST http://localhost:8080/buy -d "{\"qty\":6, \"prc\":13}"
curl localhost:8080/book
curl -H "Content-Type: application/json" -X POST http://localhost:8080/sell -d "{\"qty\":7, \"prc\":7}"
curl localhost:8080/book
curl -H "Content-Type: application/json" -X POST http://localhost:8080/sell -d "{\"qty\":12, \"prc\":6}"
curl localhost:8080/book
```