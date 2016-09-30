package com.twu.biblioteca;

import java.util.List;

import static java.util.Arrays.asList;

public class ConsoleResource extends Resource {
    public String getWelcomeMessage() {
        return "Welcome to Biblioteca!";
    }

    @Override
    public List<Book> getBooks() {
        return asList(
                new Book("Book1", "Author1", 2000),
                new Book("Book2", "Author2", 2001)
        );
    }

    @Override
    public List<String> getMainMenuItems() {
        return asList("List Books");
    }

    @Override
    public String getInvalidOptionWarning() {
        return "Select a valid option!";
    }

}
