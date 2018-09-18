FROM openjdk:8-alpine
VOLUME /tmp
ADD ./target/friend-management-0.0.1-SNAPSHOT.jar /app/friend-management.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/friend-management.jar"]