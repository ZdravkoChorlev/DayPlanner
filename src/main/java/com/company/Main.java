package com.company;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setParameterchoice(args[0]);
        TaskManager manager = new TaskManager();
        menu.menuOptions(manager.getHashTask());
    }
}
