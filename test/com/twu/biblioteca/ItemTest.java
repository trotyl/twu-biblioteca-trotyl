package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ItemTest {

    private Item item;

    @Before
    public void setUp() throws Exception {
        item = new Item("1", "Title");
    }

    @Test
    public void should_be_able_to_checkout() {
        item.checkout();

        assertThat(item.isAvailable(), is(false));
    }

    @Test
    public void should_be_able_to_return() {
        item.checkout();

        item.doReturn();

        assertThat(item.isAvailable(), is(true));
    }

}