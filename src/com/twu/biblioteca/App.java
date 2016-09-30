package com.twu.biblioteca;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class App {
    private Proxy proxy;
    private Resource resource;
    private Account account = new Account("Anonymous", "", "", "", false);
    private List<Book> books;
    private List<Movie> movies;

    public App(Proxy proxy, Resource resource) {
        this.proxy = proxy;
        this.resource = resource;

        books = resource.getBooks();
        movies = resource.getMovies();
    }

    public Account getAccount() {
        return account;
    }

    public void start() {
        proxy.displayStatic(resource.getWelcomeMessage());
    }

    public void displayBookList() {
        List<Book> booksToShow = account.isAdmin() ?
                books :
                books.stream().filter(Item::isAvailable).collect(toList());
        proxy.displayBookList(booksToShow, account.isAdmin());
    }

    public void displayMovieList() {
        List<Movie> moviesToShow = account.isAdmin() ?
                movies :
                movies.stream().filter(Item::isAvailable).collect(toList());
        proxy.displayMovieList(moviesToShow, account.isAdmin());
    }

    public void displayAccount() {
        proxy.displayAccount(account);
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
            case 1:
                displayMovieList();
                return Status.waitingForInput;
            case 2:
                displayAccount();
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
                List<Book> booksCheckedOut = books.stream()
                        .filter(book -> book.getId().equals(bookIdToCheckout))
                        .map(book -> {
                            book.checkout(account);
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
                List<Book> booksReturned = books.stream()
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
            case "login":
                String username = tokens[1];
                String password = tokens[2];
                List<Account> accounts = resource.getAccounts().stream()
                        .filter(account -> account.getName().equals(username) && account.getPassword().equals(password))
                        .collect(toList());
                if (accounts.size() > 0) {
                    proxy.displayStatic(resource.getLoginSuccessMessage(username));
                    account = accounts.get(0);
                } else {
                    proxy.displayStatic(resource.getLoginFailMessage());
                }
                break;
            case "logout":
                account = new Account("Anonymous", "", "", "", false);
                proxy.displayStatic(resource.getLogoutMessage());
                break;
        }

        return Status.idle;
    }
}
