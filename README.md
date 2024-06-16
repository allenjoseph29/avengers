# The Avengers Superhero Management System

### Designed to manage superhero entities, built using JDK 17 and Spring Boot 3.3.0 and uses Postgres DB running in a docker container.

Endpoints:
1. POST /api/superheroes: This endpoint is used to create a new superhero. It accepts a JSON body representing the superhero to be created.  
2. GET /api/superheroes/{id}: This endpoint is used to retrieve a specific superhero by their ID.  
3. GET /api/superheroes: This endpoint is used to retrieve all superheroes. It supports pagination through the page query parameter.

### Requirements
- JDK 17
- Maven
- Docker Engine

### Running the application
1. Clone the repository
2. Run in root directory: 'mvn clean install'
3. Run in root directory: 'docker build -t avengers-service .'
4. Run in root directory: 'docker-compose up'

### Curl commands to test the api (Works well with Git Bash, or please use Postman)
1. Create a new superhero:
```
 curl -X POST --location 'http://localhost:8080/api/superheroes' \
--header 'Content-Type: application/json' \
--data '{
    "alias": "Hawkeye",
    "name": "Clint Barton",
    "powers": ["master-archer", "expert-marksman", "expert-tactician"],
    "weapons": ["bow-and-arrow", "trick-arrows", "sword"],
    "origin": "Trained as a circus performer, later recruited by SHIELD",
    "associations": ["avengers", "shield", "black-widow"]
}
'
```

2. Retrieve a specific superhero:
```
curl -X GET --location 'http://localhost:8080/api/superheroes/4' 
```

3. Retrieve all superheroes:
```
 curl -X GET --location 'http://localhost:8080/api/superheroes?page=1'
```