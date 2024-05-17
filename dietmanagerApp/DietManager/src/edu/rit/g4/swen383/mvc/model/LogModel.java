package edu.rit.g4.swen383.mvc.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LogModel implements Model {

    // ArrayList to store Log objects
    private ArrayList<Log> logs;

    // Constructor initializes logs ArrayList
    public LogModel() {
        this.logs = new ArrayList<>();
    }

    // Method to add Log objects based on input info
    @Override
    public void add(String[] info) {
        this.logs.add(new Log(info)); // Create and add a new Log object
    }

    // Getter for logs ArrayList
    public ArrayList<Log> getLogs() {
        return this.logs;
    }

    /**
     * Loads data from log.csv file (if available).
     *
     * @param filePath The path to the log.csv file
     */
    public void getData(String filePath) {
        // File object representing the log.csv file
        File logFile = new File(filePath);
        try {
            // Scanner to read from the file
            Scanner in = new Scanner(logFile);

            // Read each line from the file
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] info = line.split(",");
                add(info); // Call add method to process and add data
            }

            in.close(); // Close the scanner
        } catch (FileNotFoundException e) {
            // Print message if log.csv file not found (which is okay if it's not required)
            System.out.println("log.csv File not found.");
            System.exit(0); // Exit program if file not found
        }
    }
}
