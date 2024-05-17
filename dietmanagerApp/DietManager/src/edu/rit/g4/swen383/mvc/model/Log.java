package edu.rit.g4.swen383.mvc.model;

public class Log extends LogModel {

  // Attributes to store log information
  private int year;
  private int month;
  private int day;
  private String name;
  private float count;
  private float weight;
  private float calories;
  private String type;

  // Constructor to initialize log attributes based on input information
  public Log(String[] info) {
    // Parse and assign log attributes from the input array
    this.type = info[3];
    this.year = Integer.parseInt(info[0]);
    this.month = Integer.parseInt(info[1]);
    this.day = Integer.parseInt(info[2]);
    // Depending on the type of log, assign additional attributes
    if (type.equals("f")) {
      this.name = info[4];
      this.count = Float.parseFloat(info[5]);
    } else if (type.equals("w")) {
      this.weight = Float.parseFloat(info[4]);
    } else if (type.equals("c")) {
      this.calories = Float.parseFloat(info[4]);
    }
  }

  // Getter and setter methods for log attributes
  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public float getWeight() {
    return weight;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public float getCalories() {
    return calories;
  }

  public void setCalories(float calories) {
    this.calories = calories;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  // Override the add method inherited from LogModel (empty implementation)
  @Override
  public void add(String[] info) {
    // This method is left empty because the Log class doesn't need to add data.
  }
}
