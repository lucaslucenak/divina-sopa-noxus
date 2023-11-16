FROM openjdk:17
EXPOSE 8080
ADD target/noxus-docker.jar noxus-docker.jar
ENTRYPOINT ["java", "-jar", "/noxus-docker.jar"]