package com.twu.biblioteca;

public class App {
    private Proxy proxy;

    public App(Proxy proxy) {
        this.proxy = proxy;
    }

    public void start() {
        proxy.displayStatic(StaticContent.getWelcomeMessage());
    }
}
