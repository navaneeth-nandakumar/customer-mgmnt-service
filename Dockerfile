FROM openjdk:8
EXPOSE 8080
ADD target/customer-mgmnt-svc.jar customer-mgmnt-svc.jar
ENTRYPOINT ["java","-jar","/customer-mgmnt-svc.jar"]