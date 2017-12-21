FROM openjdk:8
ADD target/t2t-api-docker.jar t2t-api-docker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "t2t-api-docker.jar"]
