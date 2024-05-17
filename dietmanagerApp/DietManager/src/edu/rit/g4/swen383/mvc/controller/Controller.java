package edu.rit.g4.swen383.mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import edu.rit.g4.swen383.mvc.model.BasicFood;
import edu.rit.g4.swen383.mvc.model.Exercise;
import edu.rit.g4.swen383.mvc.model.ExerciseModel;
import edu.rit.g4.swen383.mvc.model.Food;
import edu.rit.g4.swen383.mvc.model.LogModel;
import edu.rit.g4.swen383.mvc.model.Model;
import edu.rit.g4.swen383.mvc.model.Recipe;
import edu.rit.g4.swen383.mvc.view.View;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Controller {

    private Food foodModel;
    private LogModel logModel;
    private ExerciseModel exerciseModel;
    private View view;
    private ActionListener comboBoxActionListener;
    private ActionListener makeBasicFoodActionListener;
    private ActionListener makeRecipeActionListener;
    private ActionListener addNewFoodListener;
    private ActionListener dailyComboBoxActionListener;
    private ActionListener intakeComboBoxActionListener;
    private ActionListener ExerciseSelectionListener;
    private PropertyChangeListener dateChangeListener;
    private ActionListener weightInputListener;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    private float totalCalories = 0;
    private float totalFat = 0;
    private float totalCarbs = 0;
    private float totalProtein = 0;

    public Controller(Food foodModel, LogModel logModel, ExerciseModel exerciseModel, View view) {
        this.foodModel = foodModel;
        this.logModel = logModel;
        this.exerciseModel = exerciseModel;
        this.view = view;

        comboBoxActionListener = createComboBoxActionListener();
        view.getComboBox().addActionListener(comboBoxActionListener);

        weightInputListener = createWeightInputListener();
        view.getWeightTextField().addActionListener(weightInputListener);

        // dailyComboBoxActionListener = makeDailyIntakeActionListener();
        view.getIntakeComboBox().addActionListener(dailyComboBoxActionListener);

        makeBasicFoodActionListener = makeBasicFoodActionListener();
        view.getMakeBasicFoodButton().addActionListener(makeBasicFoodActionListener);

        dateChangeListener = dateChangeListener();
        view.getModel().addPropertyChangeListener(dateChangeListener);

        makeRecipeActionListener = makeRecipeActionListener();
        view.getMakeRecipeButton().addActionListener(makeRecipeActionListener);

        addNewFoodListener = addNewFoodListener();
        view.getAddNewFoodButton().addActionListener(addNewFoodListener);

        intakeComboBoxActionListener = intakeComboBoxActionListener();
        view.getIntakeComboBox().addActionListener(intakeComboBoxActionListener);

        ExerciseSelectionListener = createExerciseComboBoxListener();        
        view.getExerciseComboBox().addActionListener(ExerciseSelectionListener);
    }

    public void updateView() {
        view.getComboBox().removeAllItems();
        view.getIntakeComboBox().removeAllItems();
        view.getRecipeFoodComboBox().removeAllItems();
        for (BasicFood item : foodModel.getBasicFoods()) {
            view.getComboBox().addItem(item.getName());
            view.getIntakeComboBox().addItem(item.getName());
            view.getRecipeFoodComboBox().addItem(item.getName());
        }
        for (Recipe item : foodModel.getRecipes()) {
            view.getComboBox().addItem(item.getName());
            view.getIntakeComboBox().addItem(item.getName());
        }

        for(Exercise exercise : exerciseModel.getExercises()) {
            view.getExerciseComboBox().addItem(exercise.getName());
        }
    }

    private Model findModel(String name) {
        for (BasicFood item : foodModel.getBasicFoods()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        for (Recipe item : foodModel.getRecipes()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        for (Exercise item : exerciseModel.getExercises()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }

    private ActionListener createExerciseComboBoxListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                @SuppressWarnings("unchecked")
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedExerciseName = (String) comboBox.getSelectedItem();
                Model selectedModel = findModel(selectedExerciseName);
                if (selectedModel != null && selectedModel instanceof Exercise) {
                    Exercise selectedExercise = (Exercise) selectedModel;
                    writeExercise(selectedExercise);
                }
            }
        };
    }
    
    

    private ActionListener intakeComboBoxActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedFoodName = (String) comboBox.getSelectedItem();
                Model selectedModel = findModel(selectedFoodName);
                if (selectedModel != null && selectedModel instanceof BasicFood) {
                    BasicFood selectedFood = (BasicFood) selectedModel;
                    addFoodToIntake(selectedFood);
                }
            }
        };
    }

    private ActionListener createComboBoxActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedFoodName = (String) comboBox.getSelectedItem();
                Model selectedModel = findModel(selectedFoodName);
                if (selectedModel != null && selectedModel instanceof BasicFood) {
                    BasicFood selectedFood = (BasicFood) selectedModel;
                    view.getTextArea()
                            .setText(String.format("Name: %s\nCalories: %.2f\nFat: %.2f\nCarbs: %.2f\nProtein: %.2f",
                                    selectedFood.getName(), selectedFood.getCalories(),
                                    selectedFood.getFat(), selectedFood.getCarbs(), selectedFood.getProtein()));
                }
            }
        };
    }

    public ActionListener makeBasicFoodActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getNameTextField().getText().trim();
                float calories = Float.parseFloat(view.getCaloriesTextField().getText());
                float fat = Float.parseFloat(view.getFatTextField().getText());
                float carbs = Float.parseFloat(view.getCarbsTextField().getText());
                float protein = Float.parseFloat(view.getProteinTextField().getText());
                foodModel.add(new String[] { "b", name, String.valueOf(calories),
                        String.valueOf(fat), String.valueOf(carbs), String.valueOf(protein) });
                // Write the new entry to the CSV file
                File csvFile = new File("src/edu/rit/g4/swen383/mvc/data/foods.csv");
                try (FileWriter fw = new FileWriter(csvFile, true)) {
                    fw.write(String.format("\nb,%s,%.2f,%.2f,%.2f,%.2f%n", name, calories, fat, carbs, protein));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error writing to foods.csv file: " + ex.getMessage());
                }
                updateView();
                // Notify the user that the food has been added successfully
                JOptionPane.showMessageDialog(null, "Food added successfully!");
                // Clear text fields in the view
                view.getNameTextField().setText("");
                view.getCaloriesTextField().setText("");
                view.getFatTextField().setText("");
                view.getProteinTextField().setText("");
                view.getCarbsTextField().setText("");
            }
        };
    }

    private ActionListener addNewFoodListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foodModel.getRecipes().add(new Recipe("INIT RECIPE"));
                String selectedFood = (String) view.getComboBox().getSelectedItem();
                if (selectedFood != null && !selectedFood.isEmpty()) {
                    String currentText = view.getTextArea1().getText();
                    if (!currentText.isEmpty()) {
                        currentText += ", ";
                    }
                    currentText += selectedFood;
                    view.getTextArea1().setText(currentText);

                    if (view.getRecipeFoodCountTextField() != null
                            && !view.getRecipeFoodCountTextField().getText().isEmpty()) {
                        currentText += "," + Double.parseDouble(view.getRecipeFoodCountTextField().getText());
                    }

                    view.getTextArea1().setText(currentText);
                }
                BasicFood food = (BasicFood) findModel(selectedFood);
                Recipe recipe = (Recipe) findModel("INIT RECIPE");
                recipe.addBasicFood(food);
            }
        };
    }

    public PropertyChangeListener dateChangeListener() {
        return new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("value".equals(evt.getPropertyName())) {
                    Date selectedDate = (Date) evt.getNewValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd");
                    String formattedDate = sdf.format(selectedDate);
                    ArrayList<String[]> logData = loadLogDataForDate(formattedDate);

                    if (logData.isEmpty()) {
                        view.getLogTextArea().setText("No daily intake for selected date");
                    } else {
                        String intakeInfo = aggregateIntakeInfo(logData);
                        view.getLogTextArea().setText(intakeInfo);
                    }
                    if (selectedDate != null) {
                        System.out.println(dateFormatter.format(selectedDate));
                    } else {
                        System.out.println("No date selected.");
                    }
                }
            }
        };
    }

    private ActionListener makeRecipeActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String recipeName = view.getRecipeNameTextField().getText();
                String recipeContents = view.getTextArea1().getText();

                Recipe recipe = (Recipe) findModel("INIT RECIPE");
                recipe.setName(recipeName);
                addToCSV(recipeName, recipeContents);
                updateView();
            }
        };
    }

    private ActionListener createWeightInputListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float weight = Float.parseFloat(view.getWeightTextField().getText());
                    calculateDailyIntake(weight);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid weight.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private void calculateDailyIntake(float weight) {
        String selectedFood = (String) view.getIntakeComboBox().getSelectedItem();
        Model foodModel = findModel(selectedFood);
        if (foodModel != null && foodModel instanceof BasicFood) {
            BasicFood selectedFoodItem = (BasicFood) foodModel;
            float caloriesConsumed = selectedFoodItem.getCalories() * (weight / 100); // Adjust calories based on weight
            float fatConsumed = selectedFoodItem.getFat() * (weight / 100); // Adjust fat based on weight
            float carbsConsumed = selectedFoodItem.getCarbs() * (weight / 100); // Adjust carbs based on weight
            float proteinConsumed = selectedFoodItem.getProtein() * (weight / 100); // Adjust protein based on weight

            // Update the view with the calculated daily intake
            view.getLogTextArea().setText(
                    String.format("Total Calories: %.2f\nTotal Fat: %.2f\nTotal Carbs: %.2f\nTotal Protein: %.2f",
                            caloriesConsumed, fatConsumed, carbsConsumed, proteinConsumed));
        }
    }

    /**
     * Method to add recipe to the CSV file.
     */
    private void addToCSV(String recipeName, String recipeContents) {
        try (FileWriter writer = new FileWriter("src/edu/rit/g4/swen383/mvc/data/foods.csv", true)) {
            writer.append("\nr," + recipeName + "," + recipeContents);
            JOptionPane.showMessageDialog(null, "Recipe added successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error occurred while adding recipe to CSV: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private ArrayList<String[]> loadLogDataForDate(String formattedDate) {
        ArrayList<String[]> filteredLogData = new ArrayList<>();
        File logFile = new File("src/edu/rit/g4/swen383/mvc/data/log.csv");

        try (Scanner scanner = new Scanner(logFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                String logDate = data[0];
                if (logDate.equals(formattedDate)) {
                    filteredLogData.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("The log file was not found.");
        }

        return filteredLogData;
    }
    

    private String aggregateIntakeInfo(ArrayList<String[]> logData) {
        float totalCalories = 0;
        float totalFat = 0;
        float totalCarbs = 0;
        float totalProtein = 0;

        for (String[] data : logData) {
            totalCalories += Float.parseFloat(data[3]);
            totalFat += Float.parseFloat(data[4]);
            totalCarbs += Float.parseFloat(data[5]);
            totalProtein += Float.parseFloat(data[6]);
        }

        return String.format("Total Calories: %.2f\nTotal Fat: %.2f\nTotal Carbs: %.2f\nTotal Protein: %.2f",
                totalCalories, totalFat, totalCarbs, totalProtein);
    }

    private void addFoodToIntake(BasicFood food) {
        totalCalories += food.getCalories();
        totalFat += food.getFat();
        totalCarbs += food.getCarbs();
        totalProtein += food.getProtein();

        String weightText = view.getWeightTextField().getText().isEmpty() ? ""
                : "Weight: " + view.getWeightTextField().getText() + "\n";
        String updatedText = weightText + String.format(
                "Total Calories: %.2f\nTotal Fat: %.2f\nTotal Carbs: %.2f\nTotal Protein: %.2f",
                totalCalories, totalFat, totalCarbs, totalProtein);

        view.getLogTextArea().setText(updatedText);
    }

    private void writeExercise(Exercise exercise) {
        String name = exercise.getName();
        String calories = exercise.getCalories();

        String exerciseText = String.format("Name: %s\nCalories: %s", name, calories);
        view.getExerciseDetailsTextArea().setText(exerciseText);
    }


}
