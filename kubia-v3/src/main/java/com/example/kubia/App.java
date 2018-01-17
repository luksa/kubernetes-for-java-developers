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

    public static void main(String[] args) throws Exception {
        System.out.println("Kubia server v3 starting... (initialization takes 5 seconds)");
        Thread.sleep(5000);
        System.out.println("Initialization finished.");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MainHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MainHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            System.out.println("Received request from " + t.getRemoteAddress().getAddress().getHostAddress());
            String response = "You've hit v3 on " + InetAddress.getLocalHost().getHostName() + "\n";
            sendResponse(t, response);
            Blinkt.flashLED();
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