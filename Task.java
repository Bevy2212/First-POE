package com.mycompany.ndprog;

import java.util.List;

public final class Task {

    private static int taskCounter = 0; // Auto-incrementing task number
    private final String taskName;
    private final String taskDescription;
    private final String developerDetails;
    final int estimatedDuration; // Renamed from duration to estimatedDuration
    final String status;
    private final String taskId;

    // Constructor
    public Task(String taskName, String taskDescription, String developerDetails, int estimatedDuration, String status) {
        if (!checkTaskDescription(taskDescription)) {
            throw new IllegalArgumentException("Please enter a task description of less than 50 characters");
        }

        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.estimatedDuration = estimatedDuration; // Updated assignment
        this.status = status;
        this.taskId = createTaskID();
        taskCounter++;
    }

    public static void resetTaskCounter() {
        taskCounter = 0;
    }

    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    private String createTaskID() {
        String titlePart = taskName.substring(0, 2).toUpperCase();
        String assignedPart = developerDetails.split(" ")[1].substring(0, 3).toUpperCase();
        return String.format("%s:%d:%s", titlePart, taskCounter + 1, assignedPart);
    }

    public String printTaskDetails() {
        return "Task Name: " + taskName +
                "\nTask Description: " + taskDescription +
                "\nDeveloper Details: " + developerDetails +
                "\nEstimated Duration: " + estimatedDuration + " hours" +
                "\nStatus: " + status +
                "\nTask ID: " + taskId;
    }

    public static int returnTotalHours(List<Task> tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.estimatedDuration;
        }
        return totalHours;
    }

    public String getTaskID() {
        return taskId;
    }
}