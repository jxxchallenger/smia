#Start with a base image containing Java runtime
FROM eclipse-temurin:17

# Add Maintainer Info
LABEL maintainer="jxxchallenger<jxxchallenger@foxmail.com>"

# The application's jar file
ARG JAR_FILE

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

#execute the application
ENTRYPOINT ["java","-jar","/app.jar"]