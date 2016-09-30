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
                new Book("Book1"),
                new Book("Book2")
        );
    }

}
