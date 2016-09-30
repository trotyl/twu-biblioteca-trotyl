package com.twu.biblioteca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConsoleProxy proxy = new ConsoleProxy();
        ConsoleResource resource = new ConsoleResource();
        Scanner scanner = new Scanner(System.in);
        App app = new App(proxy, resource);

        app.start();
        Status status = Status.idle;

        while (status != Status.quit) {
            app.showMenuItems();
            int option = scanner.nextInt();
            status = app.run(option - 1);
            while (status == Status.waitingForInput) {
                scanner.nextLine();
                String input = scanner.nextLine();
                status = app.execute(input);
            }
        }
    }
}
