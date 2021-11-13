FROM openjdk:12-jdk-alpine

EXPOSE 8001

RUN mkdir /app

COPY build/libs/*.jar /app/stockAdapterService.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/stockAdapterService.jar"]