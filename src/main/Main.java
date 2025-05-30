package main;

import main.ui.MainMenuUI;

/**
 * Main class to start the application.
 * It initializes the main menu UI and starts the application.
 */
public class Main {
    public static void main(String[] args) {
        MainMenuUI ui = new MainMenuUI();
        ui.mainMenu();
    }
}
