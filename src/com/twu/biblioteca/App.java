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
                String bookIdToCheckout = tokens[1];
                List<Book> booksCheckedOut = resource.getBooks().stream()
                        .filter(book -> book.getId().equals(bookIdToCheckout))
                        .map(book -> {
                            book.checkout();
                            String message = resource.getCheckoutSuccessMessage(book);
                            proxy.displayStatic(message);
                            return book;
                        })
                        .collect(toList());
                if (booksCheckedOut.size() == 0) {
                    proxy.displayStatic(resource.getCheckoutFailMessage());
                }
                break;
            case "return":
                String bookIdToReturn = tokens[1];
                List<Book> booksReturned = resource.getBooks().stream()
                        .filter(book -> book.getId().equals(bookIdToReturn))
                        .map(book -> {
                            book.doReturn();
                            String message = resource.getReturnSuccessMessage(book);
                            proxy.displayStatic(message);
                            return book;
                        })
                        .collect(toList());
                if (booksReturned.size() == 0) {
                    proxy.displayStatic(resource.getReturnFailMessage());
                }
                break;
        }

        return Status.idle;
    }
}
