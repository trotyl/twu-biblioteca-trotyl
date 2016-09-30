package com.twu.biblioteca;

public class App {
    private Proxy proxy;
    private Resource resource;

    public App(Proxy proxy, Resource resource) {
        this.proxy = proxy;
        this.resource = resource;
    }

    public void start() {
        proxy.displayStatic(resource.getWelcomeMessage());
    }

    public void displayBookList() {
        proxy.displayBookList(resource.getBooks());
    }
}
