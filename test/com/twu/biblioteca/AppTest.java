package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static com.twu.biblioteca.Status.idle;
import static com.twu.biblioteca.Status.quit;
import static com.twu.biblioteca.Status.waitingForInput;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class AppTest {
    App app;
    @Mock
    Proxy proxy;
    @Mock
    Resource resource;

    private List<Book> books;
    private List<Movie> movies;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        app = new App(proxy, resource);

        Book book1 = new Book("1", "aBook", "anAuthor", 2012);
        Book book2 = new Book("2", "anotherBook", "anotherAuthor", 2016);
        book2.checkout();

        books = asList(spy(book1), spy(book2));
        when(resource.getBooks()).thenReturn(books);

        Movie movie1 = new Movie("6", "Movie1");
        Movie movie2 = new Movie("7", "Movie2");
        movie2.checkout();

        movies = asList(spy(movie1), spy(movie2));
        when(resource.getMovies()).thenReturn(movies);
    }

    @Test
    public void should_show_welcome_message() {
        when(resource.getWelcomeMessage()).thenReturn("This is welcome message.");

        app.start();

        verify(proxy).displayStatic("This is welcome message.");
    }

    @Test
    public void should_list_books() {
        app.displayBookList();

        verify(proxy).displayBookList(asList(books.get(0)));
    }

    @Test
    public void should_show_menu() {
        List<String> menuItems = asList("Option1", "Option2");
        when(resource.getMainMenuItems()).thenReturn(menuItems);

        app.showMenuItems();

        verify(proxy).displayMainMenuItems(menuItems);
    }

    @Test
    public void should_run_with_list_books() {
        app = spy(app);

        Status result = app.run(0);

        verify(app).displayBookList();
        assertThat(result, is(waitingForInput));
    }

    @Test
    public void should_warn_with_invalid_option() {
        String warning = "Some warning";
        when(resource.getInvalidOptionWarning()).thenReturn(warning);

        Status result = app.run(99999);

        verify(proxy).displayStatic(warning);
        assertThat(result, is(idle));
    }

    @Test
    public void should_be_able_to_quit() {
        Status result = app.run(-1);

        assertThat(result, is(quit));
    }

    @Test
    public void should_be_able_to_checkout_item() {
        String message = "Checked out some item";
        when(resource.getCheckoutSuccessMessage(any(Item.class))).thenReturn(message);

        app.run(0);
        app.execute("checkout 1");

        verify(proxy).displayStatic(message);
        verify(books.get(0)).checkout();
    }

    @Test
    public void should_show_warning_when_checkout_failed() {
        String message = "Checkout failed";
        when(resource.getCheckoutFailMessage()).thenReturn(message);

        app.run(0);
        app.execute("checkout 9999");

        verify(proxy).displayStatic(message);
    }

    @Test
    public void should_not_display_books_not_available() {
        app.run(0);
        app.execute("checkout 1");

        app.displayBookList();

        verify(proxy).displayBookList(Collections.emptyList());
    }

    @Test
    public void should_be_able_to_return_item() {
        String message = "Return some book";
        when(resource.getReturnSuccessMessage(any(Item.class))).thenReturn(message);

        app.run(0);
        app.execute("return 1");

        verify(proxy).displayStatic(message);
        verify(books.get(0)).doReturn();
    }

    @Test
    public void should_show_warning_when_return_failed() {
        String message = "Checkout failed";
        when(resource.getReturnFailMessage()).thenReturn(message);

        app.run(0);
        app.execute("return 9999");

        verify(proxy).displayStatic(message);
    }

    @Test
    public void should_be_able_to_list_movies() {
        app.displayMovieList();

        verify(proxy).displayMovieList(asList(movies.get(0)));
    }

    @Test
    public void should_run_with_list_movies() {
        app = spy(app);

        Status result = app.run(1);

        verify(app).displayMovieList();
        assertThat(result, is(waitingForInput));
    }

    @Test
    public void should_be_able_to_see_account() {
        app.displayAccount();

        verify(proxy).displayAccount(null);
    }

    @Test
    public void should_run_with_display_account() {
        app = spy(app);

        Status result = app.run(2);

        verify(app).displayAccount();
        assertThat(result, is(waitingForInput));
    }

    @Test
    public void should_be_able_to_login() {
        String message = "Login success";
        Account account = new Account("user", "123", "a@a.com", "...", false);
        when(resource.getAccounts()).thenReturn(asList(account));
        when(resource.getLoginSuccessMessage(anyString())).thenReturn(message);

        app.run(0);
        app.execute("login user 123");

        verify(proxy).displayStatic(message);
        assertThat(app.getAccount(), is(account));
    }

    @Test
    public void should_warn_when_login_failed() {
        String message = "Login failed";
        Account account = new Account("user", "123", "a@a.com", "...", false);
        when(resource.getAccounts()).thenReturn(asList(account));
        when(resource.getLoginFailMessage()).thenReturn(message);

        app.run(0);
        app.execute("login user 456");

        verify(proxy).displayStatic(message);
    }

    @Test
    public void should_be_able_to_logout() {
        String message = "Logout";
        Account account = new Account("user", "123", "a@a.com", "...", false);
        when(resource.getAccounts()).thenReturn(asList(account));
        when(resource.getLogoutMessage()).thenReturn(message);

        app.run(0);
        app.execute("login user 123");
        app.execute("logout");

        verify(proxy).displayStatic(message);
        assertThat(app.getAccount(), is(nullValue()));
    }
}