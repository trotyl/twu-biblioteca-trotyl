package com.twu.biblioteca;

import java.util.List;

public abstract class Resource {
    public abstract String getWelcomeMessage();

    public abstract List<Book> getBooks();

    public abstract List<String> getMainMenuItems();

    public abstract String getInvalidOptionWarning();

    public abstract String getCheckoutSuccessMessage(Item item);

    public abstract String getCheckoutFailMessage();

    public abstract String getReturnSuccessMessage(Item item);

    public abstract String getReturnFailMessage();

    public abstract List<Movie> getMovies();

    public abstract String getLoginSuccessMessage(String username);

    public abstract List<Account> getAccounts();

    public abstract String getLoginFailMessage();

    public abstract String getLogoutMessage();
}
