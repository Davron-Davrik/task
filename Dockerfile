FROM openjdk:17-alpine
EXPOSE 8888
ADD target/fergana.jar fergana.jar
ENTRYPOINT ["java","-jar","fergana.jar"]
