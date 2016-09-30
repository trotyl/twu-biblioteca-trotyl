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
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class AppTest {
    App app;
    @Mock
    Proxy proxy;
    @Mock
    Resource resource;

    List<Book> books;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        app = new App(proxy, resource);
        Book book1 = new Book("1", "aBook", "anAuthor", 2012);
        Book book2 = new Book("2", "anotherBook", "anotherAuthor", 2016);
        book2.checkout();

        books = asList(spy(book1), spy(book2));
        when(resource.getBooks()).thenReturn(books);
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
    public void should_run_with_option() {
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
    public void should_be_able_to_checkout_book() {
        String message = "Checked out some book";
        when(resource.getCheckoutSuccessMessage(any(Book.class))).thenReturn(message);

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
    public void should_be_able_to_return_book() {
        String message = "Return some book";
        when(resource.getReturnSuccessMessage(any(Book.class))).thenReturn(message);

        app.run(0);
        app.execute("return 1");

        verify(proxy).displayStatic(message);
        verify(books.get(0)).doReturn();
    }
}