
FROM  alpine:3.3

ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:$JAVA_HOME/bin
ENV JAVA_ALPINE_VERSION 8.92.14-r0

RUN apk add --no-cache openjdk8="$JAVA_ALPINE_VERSION"
RUN mkdir /app

ADD /target/*.jar  /app/app.jar
RUN sh -c 'touch /app/app.jar'

WORKDIR /app

EXPOSE  8080
CMD ["/usr/lib/jvm/java-1.8-openjdk/bin/java",  "-jar", "app.jar"]

# Red Hat JBoss Enterprise Application Platform  > docker image jboss-eap-7.1
