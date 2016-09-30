package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AppTest {
    App app;
    @Mock
    Proxy proxy;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        app = new App(proxy);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_show_welcome_message() {
        app.start();
        verify(proxy).displayStatic("Welcome to Biblioteca!");
    }

}