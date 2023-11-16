FROM openjdk:17
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=dev
ADD target/noxus-image.jar noxus-image.jar
ENTRYPOINT ["java", "-jar", "/noxus-image.jar"]
