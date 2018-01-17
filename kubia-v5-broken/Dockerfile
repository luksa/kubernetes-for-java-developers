FROM hypriot/rpi-java:latest
COPY target/*.jar /opt/kubia/
ENTRYPOINT ["java", "-XX:+ExitOnOutOfMemoryError", "-cp", "/opt/kubia/*", "com.example.kubia.App"]
