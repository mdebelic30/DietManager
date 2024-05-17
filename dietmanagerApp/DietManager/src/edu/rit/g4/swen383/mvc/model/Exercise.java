package edu.rit.g4.swen383.mvc.model;

public class Exercise extends ExerciseModel{
    private String type = "e";
    private String name;
    private String calories;

    public Exercise(String type, String name, String calories) {
        this.type = "e";
        this.name = name;
        this.calories = calories;
    }

    public Exercise(String[] info) {
        this.type = info[0];
        this.name = info[1];
        this.calories = info[2];
    }

    public String getName() {
        return name;
    }

    public String getCalories() {
        return calories;
    }
}
