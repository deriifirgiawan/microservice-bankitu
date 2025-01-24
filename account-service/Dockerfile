FROM openjdk:17-jdk-slim

COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

COPY target/account-service-0.0.1-SNAPSHOT.jar account-service.jar

ENTRYPOINT ["/wait-for-it.sh", "discovery-server:8761", "--timeout=60", "--", "java", "-jar", "account-service.jar"]

EXPOSE 8082