package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void should_contains_detail() {
        Book book = new Book("Title", "Author", 2012);

        assertThat(book.toString(), is("Title Author 2012"));
    }
}