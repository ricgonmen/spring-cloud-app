# Spring Cloud User Application

Build the applications
mvn clean package

Launch the 4 projects (parent, eureka, gateway, service)
mvn -T 4 spring-boot:run

Check eureka server is up 
http://localhost:8761/

And the gateway and services are registered as applicantios
MS-USUARIO
SERVIDOR-GATEWAY-EUREKA	

Test REST trhoug swagger
http://localhost:8090/eureka/swagger-ui/index.html

DOCKER
How to build images, create containers and start it

docker-compose up --build

Stop the containers, remove them from Docker and remove the connected networks from it

docker-compose down