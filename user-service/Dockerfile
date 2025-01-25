FROM openjdk:17-jdk-slim

COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

ENTRYPOINT ["/wait-for-it.sh", "discovery-server:8761", "--timeout=60", "--", "java", "-jar", "user-service.jar"]

EXPOSE 8081