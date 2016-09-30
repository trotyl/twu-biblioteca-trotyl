package com.twu.biblioteca;

public class Main {

    public static void main(String[] args) {
        ConsoleProxy proxy = new ConsoleProxy();
        ConsoleResource resource = new ConsoleResource();
        App app = new App(proxy, resource);

        app.start();
        app.displayBookList();
    }
}
