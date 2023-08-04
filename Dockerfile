FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/java.aop-0.0.1-SNAPSHOT.jar app.jar
ENV URL_DB=jdbc:postgresql://postgres-db:5432/teste_sicredi \
    USER_DB=hudson \
    PASSWORD_DB=hudson123
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

