package org.example;

import org.example.httprequest.CustomWebApplicationServer;

import java.io.IOException;

/**
 * GET : /calculator?operand1=4&operator=*&operand2=6
 */
public class Main {
    public static void main(String[] args) throws IOException {
        new CustomWebApplicationServer(8080).start();
    }
}