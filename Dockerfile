FROM openjdk:11
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \JAVA_OPTS=""
WORKDIR /usr/src/app
ADD target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
