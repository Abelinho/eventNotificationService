FROM openjdk:17
LABEL maintainer="Abel Agbachi"
ADD target/eventbookingservice-0.0.1-SNAPSHOT.jar eventbookingservice.jar
ENTRYPOINT ["java","-jar","eventbookingservice.jar"]