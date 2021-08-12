FROM adoptopenjdk/openjdk11:latest
MAINTAINER com.ricgonmen
ARG JAR_FILE=servidor-directorio-eureka/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]