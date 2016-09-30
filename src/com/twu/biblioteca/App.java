package com.twu.biblioteca;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
        proxy.displayBookList(resource.getBooks().stream().filter(book -> book.isAvailable()).collect(toList()));
    }

    public void showMenuItems() {
        proxy.displayMainMenuItems(resource.getMainMenuItems());
    }

    public Status run(int option) {
        switch (option) {
            case -1:
                return Status.quit;
            case 0:
                displayBookList();
                return Status.waitingForInput;
            default:
                proxy.displayStatic(resource.getInvalidOptionWarning());
                break;
        }

        return Status.idle;
    }

    public Status execute(String command) {
        String[] tokens = command.split(" ");
        switch (tokens[0]) {
            case "0":
                break;
            case "checkout":
                String bookId = tokens[1];
                List<Book> books = resource.getBooks().stream()
                        .filter(book -> book.getId().equals(bookId))
                        .map(book -> {
                            book.checkout();
                            String message = resource.getCheckoutSuccessMessage(book);
                            proxy.displayStatic(message);
                            return book;
                        })
                        .collect(toList());
                if (books.size() == 0) {
                    proxy.displayStatic(resource.getCheckoutFailMessage());
                }
                break;

        }

        return Status.idle;
    }
}
