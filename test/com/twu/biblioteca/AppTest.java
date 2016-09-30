package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}