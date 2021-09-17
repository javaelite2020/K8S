FROM tomcat
COPY target/K8s-helloworld.war /usr/local/tomcat/webapps/

