package com.twu.biblioteca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConsoleProxy proxy = new ConsoleProxy();
        ConsoleResource resource = new ConsoleResource();
        Scanner scanner = new Scanner(System.in);
        App app = new App(proxy, resource);

        app.start();
        boolean shouldQuit = false;

        while (!shouldQuit) {
            app.showMenuItems();
            int option = scanner.nextInt();
            shouldQuit = app.run(option - 1);
        }
    }
}
