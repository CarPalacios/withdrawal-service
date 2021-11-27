FROM openjdk:11
COPY "./target/withdrawal-service-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8093
ENTRYPOINT ["java","-jar","app.jar"]