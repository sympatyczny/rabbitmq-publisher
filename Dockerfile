FROM amazoncorretto:17-alpine3.12-jdk as builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM amazoncorretto:17-alpine
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
EXPOSE 8080
CMD java -jar *.jar --env=prod

#ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]