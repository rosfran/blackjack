FROM maven:3.8-openjdk-11-slim as builder

WORKDIR /app

## don't do tests now, just running command line game
##RUN mvn -DskipTests=true clean compile package

COPY pom.xml .
# To resolve dependencies in a safe way (no re-download when the source code changes)
#RUN mvn clean package -Dmaven.main.skip -Dmaven.test.skip && rm -r target

RUN mvn -B -f pom.xml dependency:go-offline
# Copy all other project files and build project
COPY . /app
RUN mvn -B install -DskipTests=true


FROM openjdk:11-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar /app

#run the app
ENV JAVA_OPTS ""
CMD [ "bash", "-c", "java ${JAVA_OPTS} -jar *.jar -v"]
#CMD  ["java", "-jar", "blackjack-1.0.jar" ]
