# Pull base image from docker.io registry, which is default registry.
FROM tomcat:8-jre8


MAINTAINER "Mukund Chavali github.com/mukundcs1611"

EXPOSE 8080
#Delete root folder , to serve our app from root
RUN  rm -rf /usr/local/tomcat/webapps/ROOT
# Let's copy our WAR to our newly created Tomcat image
ADD ./target/courseservice.war /usr/local/tomcat/webapps/ROOT.war




