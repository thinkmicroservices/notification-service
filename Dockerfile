# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
# grab the base image
FROM openjdk:8-jdk-alpine

# install curl
RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*

# create a working volume in tmp
VOLUME /tmp

#define the jar dependency files
ARG DEPENDENCY=target/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8080
ENTRYPOINT ["java","-cp","app:app/lib/*","com.thinkmicroservices.ri.spring.notification.NotificationApplication"]

 
