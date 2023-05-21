FROM openjdk:17-alpine
EXPOSE 8899
ADD target/home-kindergarten.jar home-kindergarten.jar
ENTRYPOINT ["java","-jar","home-kindergarten.jar"]
