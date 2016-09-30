package com.twu.biblioteca;

import java.util.List;

public abstract class Proxy {
    public abstract void displayStatic(String content);

    public abstract void displayBookList(List<Book> books, boolean isAdmin);

    public abstract void displayMainMenuItems(List<String> items);

    public abstract void displayMovieList(List<Movie> movies, boolean isAdmin);

    public abstract void displayAccount(Account account);
}
