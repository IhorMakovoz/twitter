package com.in6k.twitter;

import java.util.List;

public class ConsoleMessagePrinter {
    public static void print(List<String> messages) {
        System.out.println("All messages: ");
        for(String message : messages) {
            System.out.println(message);
        }
    }
}
