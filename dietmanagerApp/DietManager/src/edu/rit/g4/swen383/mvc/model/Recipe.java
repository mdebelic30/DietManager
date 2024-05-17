package edu.rit.g4.swen383.mvc.model;

import java.util.ArrayList;

// Recipe class representing a recipe in the application
public class Recipe extends Food {

  // Attributes to store recipe information
  private String name;
  private ArrayList<BasicFood> basicFoodList;

  // Constructor initializes recipe attributes
  public Recipe(String name) {
    this.name = name;
    this.basicFoodList = new ArrayList<>();
  }

  // Getter and setter methods for recipe name
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // Method to add a BasicFood object to the recipe
  public void addBasicFood(BasicFood basicFood) {
    this.basicFoodList.add(basicFood);
  }

  // Getter for the list of basic foods in the recipe
  public ArrayList<BasicFood> getBasicFoodList() {
    return this.basicFoodList;
  }
}
