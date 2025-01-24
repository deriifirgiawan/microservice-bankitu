FROM openjdk:17-jdk-slim

COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

ENTRYPOINT ["/wait-for-it.sh", "discovery-server:8761", "--timeout=60", "--", "java", "-jar", "api-gateway.jar"]

EXPOSE 8080