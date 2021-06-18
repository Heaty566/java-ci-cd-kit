FROM tomcat:8.5.66-jdk8-adoptopenjdk-hotspot

LABEL maintainer="heaty566@gmail.com"

ADD /dist/BookingHotel.war /usr/local/tomcat/webapps/


EXPOSE 8080
CMD ["catalina.sh", "run"]