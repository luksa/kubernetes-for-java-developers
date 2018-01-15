FROM java:8
COPY target/*.jar /opt/kubia/
ENTRYPOINT ["java", "-cp", "/opt/kubia/*", "com.example.kubia.App"]
