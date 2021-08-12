FROM adoptopenjdk/openjdk11:latest
COPY target/servidor-directorio-eureka-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar -Dspring.profiles.active=docker","/app.jar"]