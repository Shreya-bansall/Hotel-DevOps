# Use the official Tomcat 9 image as the base image
FROM tomcat:9.0.54-jdk11-openjdk

# Copy the WAR file to the Tomcat webapps directory
COPY target/devops-1.war /usr/local/tomcat/webapps/

# Expose port 8080 for the Tomcat server
EXPOSE 5900
