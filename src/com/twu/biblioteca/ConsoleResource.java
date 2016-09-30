package com.twu.biblioteca;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

public class ConsoleResource extends Resource {
    public String getWelcomeMessage() {
        return "Welcome to Biblioteca!";
    }

    @Override
    public List<Book> getBooks() {
        return asList(
                new Book("1", "Book1", "Author1", 2000),
                new Book("2", "Book2", "Author2", 2001)
        );
    }

    @Override
    public List<Movie> getMovies() {
        return asList(
                new Movie("1", "Movie1"),
                new Movie("2", "Movie2")
        );
    }

    @Override
    public String getLoginSuccessMessage(String username) {
        return format("Welcome %s", username);
    }

    @Override
    public String getLoginFailMessage() {
        return "Login failed";
    }

    @Override
    public String getLogoutMessage() {
        return "Logout";
    }

    @Override
    public List<Account> getAccounts() {
        return asList(
                new Account("admin", "123", "a@a.com", "...", true),
                new Account("user", "123", "b@b.com", "...", false)
        );
    }

    @Override
    public List<String> getMainMenuItems() {
        return asList("List Books", "List Movies", "Account");
    }

    @Override
    public String getInvalidOptionWarning() {
        return "Select a valid option!";
    }

    @Override
    public String getCheckoutSuccessMessage(Item item) {
        return format("Checkout %s successfully!", item.getTitle());
    }

    @Override
    public String getCheckoutFailMessage() {
        return "Checkout failed!";
    }

    @Override
    public String getReturnSuccessMessage(Item item) {
        return format("Return %s successfully!", item.getTitle());
    }

    @Override
    public String getReturnFailMessage() {
        return "Return failed!";
    }

}
