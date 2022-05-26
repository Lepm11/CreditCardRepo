FROM openjdk:11
VOLUME /tmp
EXPOSE 8002
ADD ./target/creditCard-0.0.1-SNAPSHOT.jar credit-server.jar
ENTRYPOINT ["java","-jar","/credit-server.jar"]