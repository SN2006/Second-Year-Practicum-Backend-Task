# Library API

## How to run the project

First of all, you should download several components:

- Java 17 (https://www.youtube.com/watch?v=y4Jz87uQYRs)
- Maven (https://www.youtube.com/watch?v=Br98iO1K1SA)
- Docker (https://docs.docker.com/get-started/get-docker/)

Then, you need to open console in root of this project adc run next command:

``
mvn clean package
``

After that you should run command:

``
docker build -t library-api .
``

And finally you run command:

``
docker run --name library-api-container -it -p 8080:8080 library-api
``

## How to stop the container

To stop the container you need to run next command:

``
docker stop library-api-container
``

And to start this container again use command:

``
docker start -i library-api-container
``

## Request examples

All request examples you can find in holder [requests](requests)

## Uml diagrams

- Class Diagram you can find [here](uml/ClassDiagram.plantuml)
- Entity Relationship Diagram you can find [here](uml/EntityRelationshipDiagram.puml)