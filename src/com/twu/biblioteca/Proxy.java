package com.twu.biblioteca;

import java.util.List;

public abstract class Proxy {
    public abstract void displayStatic(String content);

    public abstract void displayBookList(List<Book> books);

    public abstract void displayMenuItems(List<String> items);
}
