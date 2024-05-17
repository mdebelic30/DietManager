package edu.rit.g4.swen383.mvc.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Food implements Model {

  // ArrayList to store BasicFood objects
  private ArrayList<BasicFood> basicFoods;
  // ArrayList to store Recipe objects
  private ArrayList<Recipe> recipes;

  // Constructor initializes basicFoods and recipes ArrayLists
  public Food() {
    this.basicFoods = new ArrayList<>();
    this.recipes = new ArrayList<>();
  }

  public void addRecipe(String name) {
    this.recipes.add(new Recipe(name));
  }

  // Method to add BasicFood or Recipe objects based on input info
  @Override
  public void add(String[] info) {
    // Check if the line represents basic food (type 'b')
    if (info[0].equals("b")) {
      // Create BasicFood object and add it to the list
      this.basicFoods.add(new BasicFood(info));
    } else if (info[0].equals("r")) {
      // Create Recipe object and add it to the list
      this.recipes.add(new Recipe(info[1]));
    }
  }

  // Getter for basicFoods ArrayList
  public ArrayList<BasicFood> getBasicFoods() {
    return this.basicFoods;
  }

  // Getter for recipes ArrayList
  public ArrayList<Recipe> getRecipes() {
    return this.recipes;
  }

  /**
   * Loads data from foods.csv file and constructs BasicFood and Recipe objects.
   *
   * @param filePath The path to the foods.csv file
   */
  public void loadData(String filePath) {
    // File object representing the foods.csv file
    File foodsFile = new File(filePath);
    try {
      // Scanner to read from the file
      Scanner in = new Scanner(foodsFile);

      // Read each line from the file
      while (in.hasNextLine()) {
        String line = in.nextLine();
        String[] info = line.split(",");
        add(info); // Call add method to process and add data
      }

      in.close(); // Close the scanner
    } catch (FileNotFoundException e) {
      // Print error message if file not found
      System.out.println("foods.csv File not found.");
      System.exit(0); // Exit program if file not found
    }
  }
}
