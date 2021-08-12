# Spring Cloud User Application

How to build and dockerize

make clean package

How to run from comand line

Launch the 4 projects (parent, eureka, gateway, service)
mvn -T 4 spring-boot:run

Check eureka server is up 
http://localhost:8761/

And the gateway and services are registered as applicantios
MS-USUARIO
SERVIDOR-GATEWAY-EUREKA	

Test REST trhoug swagger
http://localhost:8090/eureka/swagger-ui/index.html
