FROM openjdk:17-jdk-slim

COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

COPY target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar

ENTRYPOINT ["/wait-for-it.sh", "discovery-server:8761", "--timeout=60", "--", "java", "-jar", "auth-service.jar"]

EXPOSE 8081