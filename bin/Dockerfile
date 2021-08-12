FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=servidor-directorio-eureka/target/*.jar
COPY ${JAR_FILE} app.jar
ARG JAR_FILE=servidor-gateway-eureka/target/*.jar
COPY ${JAR_FILE} app.jar
ARG JAR_FILE=ms-users/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]