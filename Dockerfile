FROM openjdk:11
LABEL maintainer="Abel Agbachi"
ADD target/eventbookingservice-0.0.1-SNAPSHOT.jar eventbookingservice-image.jar
ENTRYPOINT ["java","-jar","eventbookingservice-image.jar"]