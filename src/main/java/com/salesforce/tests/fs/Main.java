package com.salesforce.tests.fs;

import java.util.Scanner;

/**
 * The entry point for the Test program
 */
public class Main {

    private static final String MENU_COMMANDS = "Commands...\n" +
                                                "\tquit\n"      +
                                                "\tpwd\n"       +
                                                "\tls\n"        +
                                                "\tmkdir\n"     +
                                                "\tcd\n"        +
                                                "\ttouch\n";
    private static final String MENU_INVALID_COMMAND_INTRODUCED = "Invalid command";

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println(MENU_COMMANDS);

            command = scanner.nextLine();

            if (CommandExecutor.isValidCommand(command) == true) {
                CommandExecutor.execute(command);
            }
            else {
                System.out.println(MENU_INVALID_COMMAND_INTRODUCED);
            }
        }
        while (true);
    }

    public static void main(String[] args) {
        displayMenu();
    }
}
