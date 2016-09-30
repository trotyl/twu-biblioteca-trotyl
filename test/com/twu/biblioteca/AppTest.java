package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        app = new App(proxy, resource);
    }

    @Test
    public void should_show_welcome_message() {
        when(resource.getWelcomeMessage()).thenReturn("This is welcome message.");

        app.start();

        verify(proxy).displayStatic("This is welcome message.");
    }

    @Test
    public void should_list_books() {
        List<Book> books = asList(new Book("aBook", "anAuthor", 2012));
        when(resource.getBooks()).thenReturn(books);

        app.displayBookList();

        verify(proxy).displayBookList(books);
    }

    @Test
    public void should_show_menu() {
        List<String> menuItems = asList("Option1", "Option2");
        when(resource.getMenuItems()).thenReturn(menuItems);

        app.showMenuItems();

        verify(proxy).displayMenuItems(menuItems);
    }

    @Test
    public void should_run_with_option() {
        app = spy(app);

        boolean result = app.run(0);

        verify(app).displayBookList();
        assertThat(result, is(false));
    }

    @Test
    public void should_warn_with_invalid_option() {
        app = spy(app);
        String warning = "Some warning";
        when(resource.getInvalidOptionWarning()).thenReturn(warning);

        boolean result = app.run(-1);

        verify(proxy).displayStatic(warning);
        assertThat(result, is(false));
    }
}