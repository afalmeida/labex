FROM maven:3.6.3-jdk-8

# COPY pom.xml .
# RUN mvn clean install;
COPY ./target/labex-0.0.1-SNAPSHOT.jar labex-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD java -jar labex-0.0.1-SNAPSHOT.jar --spring.profiles.active=PRODUCTION