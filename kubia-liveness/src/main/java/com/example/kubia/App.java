package com.example.kubia;

import com.example.blinktclient.Blinkt;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class App {

    private static int requestCount;

    public static void main(String[] args) throws Exception {
        System.out.println("Kubia server starting...");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            System.out.println(++requestCount + ". Received request from " + t.getRemoteAddress().getAddress().getHostAddress());
            Blinkt.flashLED();
            if (requestCount >= 10) {
                System.out.println("An error has occured!");
                throw new RuntimeException();
            }
            String response = requestCount + ". You've hit " + InetAddress.getLocalHost().getHostName() + "\n";
            sendResponse(t, response);
        }
    }

    private static void sendResponse(HttpExchange t, String response) throws IOException {
        byte[] bytes = response.getBytes();
        t.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = t.getResponseBody()) {
            os.write(bytes);
        }
    }

}