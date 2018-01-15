FROM hypriot/rpi-java:latest
COPY target/*.jar /opt/kubia/
ENTRYPOINT ["java", "-cp", "/opt/kubia/*", "com.example.kubia.Client"]
