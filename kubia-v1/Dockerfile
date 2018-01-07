FROM hypriot/rpi-java:latest
COPY target/kubia-v1-1.0-SNAPSHOT.jar /opt/kubia/
ENTRYPOINT ["java", "-cp", "/opt/kubia/kubia-v1-1.0-SNAPSHOT.jar", "com.example.kubia.App"]
