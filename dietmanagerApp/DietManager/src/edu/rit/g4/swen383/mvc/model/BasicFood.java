package edu.rit.g4.swen383.mvc.model;

/**
 * The BasicFood class represents a basic food item with nutritional
 * information.
 * It implements the Model interface.
 */
public class BasicFood extends Food {

  // Name of the food item
  private String name;
  // Nutritional information
  private float calories;
  private float fat;
  private float carbs;
  private float protein;

  /**
   * Constructor for the BasicFood class.
   *
   * @param info An array containing information about the food item
   */
  public BasicFood(String[] info) {
    // Initialize attributes from the provided information array
    this.name = info[1];
    this.calories = Float.parseFloat(info[2]);
    this.fat = Float.parseFloat(info[3]);
    this.carbs = Float.parseFloat(info[4]);
    this.protein = Float.parseFloat(info[5]);
  }

  /**
   * Getter method for the name of the food item.
   *
   * @return The name of the food item
   */
  public String getName() {
    return name;
  }

  /**
   * Setter method for the name of the food item.
   *
   * @param name The name of the food item to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter method for the number of calories in the food item.
   *
   * @return The number of calories
   */
  public float getCalories() {
    return calories;
  }

  /**
   * Setter method for the number of calories in the food item.
   *
   * @param calories The number of calories to set
   */
  public void setCalories(float calories) {
    this.calories = calories;
  }

  /**
   * Getter method for the amount of fat in the food item.
   *
   * @return The amount of fat
   */
  public float getFat() {
    return fat;
  }

  /**
   * Setter method for the amount of fat in the food item.
   *
   * @param fat The amount of fat to set
   */
  public void setFat(float fat) {
    this.fat = fat;
  }

  /**
   * Getter method for the amount of carbohydrates in the food item.
   *
   * @return The amount of carbohydrates
   */
  public float getCarbs() {
    return carbs;
  }

  /**
   * Setter method for the amount of carbohydrates in the food item.
   *
   * @param carbs The amount of carbohydrates to set
   */
  public void setCarbs(float carbs) {
    this.carbs = carbs;
  }

  /**
   * Getter method for the amount of protein in the food item.
   *
   * @return The amount of protein
   */
  public float getProtein() {
    return protein;
  }

  /**
   * Setter method for the amount of protein in the food item.
   *
   * @param protein The amount of protein to set
   */
  public void setProtein(float protein) {
    this.protein = protein;
  }

  /**
   * Method required by the Model interface but not implemented for BasicFood.
   *
   * @param model The model to add
   */
  @Override
  public void add(String[] info) {
    // Method not implemented for BasicFood
  }

}
