FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar App-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ProductServiceApplication-0.0.1-SNAPSHOT.jar"]
EXPOSE 7000
