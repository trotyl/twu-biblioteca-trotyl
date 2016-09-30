package com.twu.biblioteca;

import java.util.List;

import static java.lang.String.format;

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

        System.out.print("Please enter an option or nothing to go back:");
    }

    @Override
    public void displayMovieList(List<Movie> movies) {
        System.out.println("The current available movies are:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        System.out.println();

        System.out.print("Please enter an option or nothing to go back:");
    }

    @Override
    public void displayAccount(Account account) {
        if (account != null) {
            System.out.println(format("Hello, %s", account.getName()));
            System.out.println(format("Your email is: %s", account.getEmail()));
            System.out.println(format("Your phone is: %s", account.getPhone()));
            System.out.println();
            System.out.print("Enter 'logout' to logout:");
        } else {
            System.out.println("You are not logged in!");
            System.out.println();
            System.out.print("Enter 'login <username> <password>' to login:");
        }
    }

    @Override
    public void displayMainMenuItems(List<String> items) {
        System.out.println("The available options are:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(format("%d) %s", i + 1, items.get(i)));
        }
        System.out.println("0) Quit");
        System.out.println();
        System.out.print("Please enter an option:");
    }
}
