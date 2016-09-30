package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ItemTest {

    private Item item;
    private Account account = new Account("", "", "", "", false);

    @Before
    public void setUp() throws Exception {
        item = new Item("1", "Title");
    }

    @Test
    public void should_be_able_to_checkout() {
        item.checkout(account);

        assertThat(item.isAvailable(), is(false));
        assertThat(item.getOwner(), is(account));
    }

    @Test
    public void should_be_able_to_return() {
        item.checkout(account);

        item.doReturn();

        assertThat(item.isAvailable(), is(true));
        assertThat(item.getOwner(), is(nullValue()));
    }

}