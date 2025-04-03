FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} course-management-systems.jar

ENTRYPOINT ["java", "-jar", "course-management-systems.jar"]

EXPOSE 8081