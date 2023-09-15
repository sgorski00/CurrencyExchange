package org.example.menu;

import java.util.LinkedHashMap;
import java.util.Map;

public class ListOfTasks {
    private final Map<Integer, String> tasks = new LinkedHashMap<>();
    private final Map<Integer, String> loginTasks = new LinkedHashMap<>();

    void printTasks() {
        System.out.println("Enter number");
        loadTasksList();
        for (Integer i : tasks.keySet()) {
            System.out.printf("%d. %s%n", i, tasks.get(i));
        }
    }

    private void loadTasksList() {
        tasks.put(1, "Show account balance");
        tasks.put(2, "Show sum of money");
        tasks.put(3, "Add money");
        tasks.put(4, "Remove money");
        tasks.put(5, "Set money");
        tasks.put(6, "Transfer money");
        tasks.put(7, "Actual exchange rates");
        tasks.put(9, "Logout");
        tasks.put(0, "Exit");
    }

    void printLoginMenu(){
        System.out.println("Enter number");
        loadLoginMenuTasks();
        for(Integer i : loginTasks.keySet()){
            System.out.printf("%d. %s%n", i, loginTasks.get(i));
        }
    }

    private void loadLoginMenuTasks(){
        loginTasks.put(1, "LogIn");
        loginTasks.put(2, "Register");
        loginTasks.put(3, "Change Password");
        loginTasks.put(0, "Print list of active users");
    }
}
