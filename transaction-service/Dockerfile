FROM openjdk:17-jdk-slim

COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

COPY target/transaction-service-0.0.1-SNAPSHOT.jar transaction-service.jar

ENTRYPOINT ["/wait-for-it.sh", "discovery-server:8761", "--timeout=60", "--", "java", "-jar", "transaction-service.jar"]

EXPOSE 8083