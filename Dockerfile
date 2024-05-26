# Usa una imagen base de Tomcat
FROM tomcat:9.0-jdk11

# Copia el archivo WAR de tu aplicación al directorio webapps de Tomcat
COPY target/w2m.war /usr/local/tomcat/webapps/

# Puerto en el que escucha Tomcat
EXPOSE 8080

# Especifica el comando a ejecutar al iniciar el contenedor
CMD ["catalina.sh", "run"]