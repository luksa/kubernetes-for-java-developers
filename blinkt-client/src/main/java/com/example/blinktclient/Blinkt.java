package com.example.blinktclient;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Blinkt {

    public static final String BLINKT_NODE = "172.17.0.1";
    public static final String BLINKT_PORT = "10399";
    public static final String POD_NAME = getLocalHostName();

    private static String getLocalHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "unknown";
        }
    }

    public enum Color {
        OFF, WHITE, YELLOW, ORANGE, GREEN, DARKGREEN, RED, MAGENTA, BLUE, GRAY, CURRENT
    }

    public enum Effect {
        NONE, FLASH, LONGFLASH, GODARK
    }

    private static URL getUrl(Color color, Effect effect) throws MalformedURLException {
        return new URL("http://" + BLINKT_NODE + ":" + BLINKT_PORT + "/ping?pod=" + POD_NAME + "&color=" + color.name() + "&effect=" + effect.name());
    }

    public static void flashLED() {
        flashLED(Color.WHITE);
    }

    public static void flashLED(Color color) {
        flashLED(color, Effect.FLASH);
    }

    public static void flashLED(Color color, Effect effect) {
        try {
            try (InputStream in = getUrl(color, effect).openStream()) {
            }
        } catch (Exception ex) {
            System.out.println("Could not flash LED: " + ex);
        }
    }
}
