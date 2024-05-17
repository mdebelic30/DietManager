package edu.rit.g4.swen383.mvc.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ExerciseModel implements Model{

    private List<Exercise> exercises = new ArrayList<>();

    public void loadData(String filePath) {
        // File object representing the foods.csv file
        File exerciseFile = new File(filePath);
        try {
          // Scanner to read from the file
          Scanner in = new Scanner(exerciseFile);
    
          // Read each line from the file
          while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] info = line.split(",");
            add(info); // Call add method to process and add data
          }
    
          in.close(); // Close the scanner
        } catch (FileNotFoundException e) {
          // Print error message if file not found
          System.out.println("exercise.csv File not found.");
          System.exit(0); // Exit program if file not found
        }
      }

    public List<Exercise> getExercises() {
        return exercises;
    }
    @Override
    public void add(String[] info) {
        if (info[0].equals("e")) {
            // Create BasicFood object and add it to the list
            this.exercises.add(new Exercise(info));
        } else {
            System.out.println("Error adding data");
        }

        
    }
}
