FROM openjdk:17
EXPOSE 8081
ADD target/auth-service.jar auth-service.jar
CMD ["java","-jar","auth-service.jar"]