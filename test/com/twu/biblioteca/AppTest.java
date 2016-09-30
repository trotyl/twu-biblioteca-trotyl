package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
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

}