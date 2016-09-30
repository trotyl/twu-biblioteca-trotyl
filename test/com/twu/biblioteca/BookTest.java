package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class BookTest {

    private Book book;

    @Before
    public void setUp() throws Exception {
        book = new Book("1", "Title", "Author", 2012);
    }

    @Test
    public void should_contains_detail() {
        assertThat(book.toString(), is("1 Title Author 2012"));
    }

    @Test
    public void should_be_able_to_checkout() {
        book.checkout();

        assertThat(book.isAvailable(), is(false));
    }
}