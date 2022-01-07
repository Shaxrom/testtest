FROM adoptopenjdk/openjdk11:alpine
EXPOSE 8093
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]