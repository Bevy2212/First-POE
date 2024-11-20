/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ndprog;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showInputDialog;

/**
 *
 * @author Berberly
 */

public class Main {

    public static void main(String[] args) {
        Login login = new Login();

        // User registration process
        registerUser(login);

        // User login process
        boolean loginSuccess = loginUser(login);

        // Proceed if login is successful
        if (loginSuccess) {
            showMessageDialog(null, "Welcome to EasyKanban!");
            manageTasks();
        }

        // Confirm exit when the application is about to close
        confirmExit();
    }

    private static void registerUser(Login login) {
        showMessageDialog(null, "Create an account");

        String username = getValidUsername(login);
        String password = getValidPassword(login);

        String firstName = showInputDialog("Enter first name:");
        String lastName = showInputDialog("Enter last name:");

        String registrationMessage = login.registerUser(username, password, firstName, lastName);
        showMessageDialog(null, registrationMessage);

        // Display welcome message after successful registration
        if (registrationMessage.contains("successfully")) { // Adjust this condition based on your actual success message
            showMessageDialog(null, "Welcome to EasyKanban!");
        }
    }

    private static String getValidUsername(Login login) {
        String username;

        while (true) {
            username = showInputDialog("Enter username:");
            if (login.checkUserName(username)) break;
            showMessageDialog(null, "Username must contain '_' and be no more than 5 characters long.");
        }

        return username;
    }

    private static String getValidPassword(Login login) {
        String password;

        while (true) {
            password = showInputDialog("Enter password:");
            if (login.checkPasswordComplexity(password)) break;
            showMessageDialog(null, "Password must be at least 8 characters long, contain a capital letter, a number, and a special character.");
        }

        return password;
    }

    private static boolean loginUser(Login login) {
        showMessageDialog(null, "Login to your account");

        String username = showInputDialog("Enter username:");
        String password = showInputDialog("Enter password:");

        // Remove the blank message by not showing the status dialog
        return login.loginUser(username, password);
    }

    private static void manageTasks() {
        List<Task> tasks = new ArrayList<>(); // Changed from List<Object> to List<Task>
        boolean running = true;

        while (running) {
            int choice = getTaskManagementChoice();

            switch (choice) {
                case 1 -> addTasks(tasks);
                case 2 -> showTasks(tasks); // Show existing tasks
                case 3 -> running = false; // Exit loop
                default -> showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

    private static int getTaskManagementChoice() {
        String options = "Select an option:\n1) Add tasks\n2) Show report (Coming Soon)\n3) Quit";

        while (true) {
            try {
                String choiceStr = showInputDialog(options);
                return Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Invalid choice. Please enter a number.");
            }
        }
    }

    private static void addTasks(List<Task> tasks) { // Updated parameter type
        int numTasks = getNumberOfTasks();

        for (int i = 0; i < numTasks; i++) {
            Task task = createTask();
            tasks.add(task);
            showMessageDialog(null, task.printTaskDetails());
        }

        int totalHours = Task.returnTotalHours(tasks);
        showMessageDialog(null, "Total hours: " + totalHours);
    }

    private static int getNumberOfTasks() {
        while (true) {
            try {
                String numTasksStr = showInputDialog("How many tasks do you wish to enter?");
                return Integer.parseInt(numTasksStr);
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }
    }

    private static Task createTask() {
        String taskName = showInputDialog("Enter task name:");

        String taskDescription = getValidTaskDescription();

        String developerDetails = showInputDialog("Enter developer details (First and Last Name):");

        int estimatedDuration = getValidEstimatedDuration(); // Updated variable name

        String status = selectTaskStatus();

        return new Task(taskName, taskDescription, developerDetails, estimatedDuration, status); // Updated constructor call
    }

    private static String getValidTaskDescription() {
        String description;

        while (true) {
            description = showInputDialog("Enter task description (max 50 characters):");
            if (description.length() <= 50) break;
            showMessageDialog(null, "Please enter a task description of less than 50 characters.");
        }

        return description;
    }

    private static int getValidEstimatedDuration() { // Updated method name
        while (true) {
            try {
                String durationStr = showInputDialog("Enter task estimated duration (in hours):"); // Updated prompt message
                return Integer.parseInt(durationStr);
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }
    }

    public static String selectTaskStatus() {
        int statusChoice;

        while (true) {
            try {
                String statusOptions = "Select Task Status:\n1) To Do\n2) Doing\n3) Done";
                statusChoice = Integer.parseInt(showInputDialog(statusOptions));

                switch (statusChoice) {
                    case 1 -> {
                        return "To Do";
                    }
                    case 2 -> {
                        return "Doing";
                    }
                    case 3 -> {
                        return "Done";
                    }
                    default -> throw new IllegalArgumentException(); // Invalid choice triggers exception
                }

            } catch (NumberFormatException e) {
                showMessageDialog(null, "Invalid input. Please enter a number.");
            } catch (IllegalArgumentException e) {
                showMessageDialog(null, "Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }

    private static void confirmExit() {
        int response = JOptionPane.showConfirmDialog(null,
                "Do you really want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            showMessageDialog(null, "Thank you for using EasyKanban. Goodbye!");
            System.exit(0); // Exits the application
        }
    }

    private static void showTasks(List<Task> tasks) { // Updated method to display tasks
        if (tasks.isEmpty()) {
            showMessageDialog(null, "Coming Soon");
        } else {
            StringBuilder taskList = new StringBuilder("Current Tasks:\n");
            for (Task task : tasks) {
                taskList.append(task.printTaskDetails()).append("\n"); // Use printTaskDetails for proper display
            }
            showMessageDialog(null, taskList.toString());
        }
    }
}