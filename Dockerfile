FROM openjdk:18-alpine3.15
VOLUME /tmp
COPY target/shipPlacement-0.2.0.jar shipPlacement-0.2.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "shipPlacement-0.2.0.jar"]