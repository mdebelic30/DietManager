package edu.rit.g4.swen383.mvc;

import edu.rit.g4.swen383.mvc.controller.Controller;
import edu.rit.g4.swen383.mvc.model.ExerciseModel;
import edu.rit.g4.swen383.mvc.model.Food;
import edu.rit.g4.swen383.mvc.model.LogModel;
import edu.rit.g4.swen383.mvc.view.View;

/**
 * The App class contains the main method to run the application.
 */
public class App {

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Food foodModel = new Food();
        foodModel.loadData("src/edu/rit/g4/swen383/mvc/data/foods.csv");

        LogModel logModel = new LogModel();
        logModel.getData("src/edu/rit/g4/swen383/mvc/data/log.csv");

        ExerciseModel exerciseModel = new ExerciseModel(); 
        exerciseModel.loadData("src/edu/rit/g4/swen383/mvc/data/exercise.csv"); 
        
        View view = new View();

        Controller controller = new Controller(foodModel, logModel, exerciseModel, view);
        controller.updateView();
    }

    
}
