FROM openjdk:8-jdk-alpine
ADD target/todolistvul.jar todolistvul.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","todolistvul.jar"]
