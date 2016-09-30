package com.twu.biblioteca;

import java.util.List;

public abstract class Resource {
    public abstract String getWelcomeMessage();

    public abstract List<Book> getBooks();
}