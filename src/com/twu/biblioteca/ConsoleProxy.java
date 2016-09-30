package com.twu.biblioteca;

public class ConsoleProxy extends Proxy {
    @Override
    public void displayStatic(String content) {
        System.out.println(content);
    }
}
