FROM openjdk:11-jre-slim
EXPOSE 8090
VOLUME /tmp
ARG JAR_FILE=target/salesstats-sahila-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} salesstats.jar
ENTRYPOINT ["java","-jar","/salesstats.jar"]