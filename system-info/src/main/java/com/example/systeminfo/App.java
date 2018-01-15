package com.example.systeminfo;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class App {

    public static void main(String[] args) throws Exception {

        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println();

        OperatingSystemMXBean mxbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long memorySize = mxbean.getTotalPhysicalMemorySize();
        System.out.println("Free memory:   " + toMb(Runtime.getRuntime().freeMemory()));
        System.out.println("Total memory:  " + toMb(Runtime.getRuntime().totalMemory()));
        System.out.println("Max memory:    " + toMb(Runtime.getRuntime().maxMemory()));
        System.out.println("System memory: " + toMb(memorySize));
    }

    private static String toMb(long bytes) {
        return (bytes / (1024*1024)) + "MB";
    }


}