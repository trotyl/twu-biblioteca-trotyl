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

    public void showMenuItems() {
        proxy.displayMenuItems(resource.getMenuItems());
    }

    public boolean run(int option) {
        switch (option) {
            case 0:
                displayBookList();
                break;
            default:
                proxy.displayStatic(resource.getInvalidOptionWarning());
                break;
        }

        return false;
    }
}
