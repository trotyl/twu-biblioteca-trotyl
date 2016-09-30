package com.twu.biblioteca;

import java.util.List;

public class ConsoleProxy extends Proxy {
    @Override
    public void displayStatic(String content) {
        System.out.println(content);
    }

    @Override
    public void displayBookList(List<Book> books) {
        System.out.println("The current available books are:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
