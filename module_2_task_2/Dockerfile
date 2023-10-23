FROM openjdk:17
WORKDIR usr/src/app
COPY target/module_2_task_2-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh wait-for-it.sh
CMD ["java","-jar","app.jar"]
