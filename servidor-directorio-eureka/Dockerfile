FROM adoptopenjdk/openjdk11:latest
COPY target/servidor-directorio-eureka-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/app.jar"]