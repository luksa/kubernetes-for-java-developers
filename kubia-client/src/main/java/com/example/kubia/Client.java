package com.example.kubia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Kubia client starting...");

        while (true) {
            invokeKubiaService();
            Thread.sleep(1000);
        }
    }

    private static void invokeKubiaService() {
        try {
            URL url = new URL("http://kubia.default.svc.cluster.local");
//            URL url = new URL("http://" + System.getenv("KUBIA_SERVICE_HOST") + ":" + System.getenv("KUBIA_SERVICE_PORT")):

            System.out.println("Invoking kubia service at " + url);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
                in.lines().forEach(line -> System.out.println("Response: " + line));
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

    }
}
