FROM tomcat:9-alpine

ADD target/restaurant_chooser.war /usr/local/tomcat/webapps/restaurant_chooser.war