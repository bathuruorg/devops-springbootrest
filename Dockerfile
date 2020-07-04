
FROM  alpine:3.3  as build

ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:$JAVA_HOME/bin
ENV JAVA_ALPINE_VERSION 8.92.14-r0

ENV RUN_AS_USER adminapp
ENV APP springbootrest
ENV VERSION 1.0.5.RELEASE

RUN apk add --no-cache openjdk8="$JAVA_ALPINE_VERSION"

RUN mkdir /app

ADD /target/$APP-$VERSION.jar  /app/app.jar
RUN sh -c 'touch /app/app.jar'

RUN addgroup ${RUN_AS_USER} && \
         adduser -D  -G ${RUN_AS_USER}  ${RUN_AS_USER} && \
         chown -R ${RUN_AS_USER}:${RUN_AS_USER} /app

USER adminapp
WORKDIR /app

FROM alpine
COPY --from=build   /app/   /app/
EXPOSE 8080
CMD ["/usr/lib/jvm/java-1.8-openjdk/bin/java",  "-jar", "app.jar"]

# Red Hat JBoss Enterprise Application Platform  > docker image jboss-eap-7.1
