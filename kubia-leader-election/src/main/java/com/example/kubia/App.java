package com.example.kubia;

import com.example.blinktclient.Blinkt;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.metaparticle.sync.Election;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class App {

    private static boolean master = false;

    public static void main(String[] args) throws Exception {
        System.out.println("Kubia server starting...");

        new Thread(() -> {
            Election e = new Election("kubia",
                    () -> {
                        System.out.println("I am the master.");
                        master = true;
                    },
                    () -> {
                        System.out.println("I'm no longer the master.");
                        master = false;
                    });
            e.setFlakyElectionForTesting();
            e.run();
        }).start();

        new Thread(() -> {
            while (true) {
                if (master) {
                    Blinkt.flashLED(Blinkt.Color.YELLOW, Blinkt.Effect.LONGFLASH);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                    return;
                }
            }
        }).start();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MainHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MainHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String masterOrSlave = master ? "master" : "slave ";
            System.out.println("Received request from " + t.getRemoteAddress().getAddress().getHostAddress() + " (as " + masterOrSlave + ")");
            String response = "You've hit the " + masterOrSlave + " on " + InetAddress.getLocalHost().getHostName() + "\n";
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