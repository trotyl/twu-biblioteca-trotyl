package com.twu.biblioteca;

import java.util.List;

public class ConsoleProxy extends Proxy {
    @Override
    public void displayStatic(String content) {
        System.out.println(content);
        System.out.println();
    }

    @Override
    public void displayBookList(List<Book> books) {
        System.out.println("The current available books are:");
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println();
    }

    @Override
    public void displayMenuItems(List<String> items) {
        System.out.println("The available options are:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(String.format("%d) %s", i + 1, items.get(i)));
        }
        System.out.println();
        System.out.print("Please enter an option:");
    }
}
