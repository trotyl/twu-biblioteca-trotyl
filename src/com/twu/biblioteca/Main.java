package com.twu.biblioteca;

public class Main {

    public static void main(String[] args) {
        ConsoleProxy proxy = new ConsoleProxy();
        App app = new App(proxy);
        app.start();
    }
}
