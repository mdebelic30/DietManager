package edu.rit.g4.swen383.mvc.view;

import java.awt.*;
import javax.swing.*;
import net.sourceforge.jdatepicker.impl.*;
import java.util.Calendar;

public class View {

    private JFrame frame;
    private JComboBox<String> comboBox, intakeComboBox, recipeFoodComboBox, exerciseComboBox;
    private JTextArea textArea, logTextArea, textArea1, exerciseDetailsTextArea;
    private JTextField nameTextField, proteinTextField, caloriesTextField, fatTextField, carbsTextField,
            recipeNameTextField, weightTextField, desiredCaloriesTextField, recipeFoodCountTextField;
    private JButton makeBasicFoodButton;
    private JButton makeRecipeButton, addNewFoodButton;
    private JDatePickerImpl datePicker;
    private UtilDateModel model;
    private JButton addToDailyIntakeButton;
    private JButton saveIntakeButton;
    private JLabel exerciseDetails;


    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame("Diet Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        model = new UtilDateModel();
        Calendar today = Calendar.getInstance();

        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        
        exerciseComboBox = new JComboBox<>();
        exerciseDetails = new JLabel("Select an exercise to see details");
        exerciseComboBox = new JComboBox<>();
        exerciseDetailsTextArea = new JTextArea(5, 20);  // Set preferred size as needed
        exerciseDetailsTextArea.setEditable(false);
        exerciseDetailsTextArea.setLineWrap(true);
        exerciseDetailsTextArea.setWrapStyleWord(true);


        model.setDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);

        comboBox = new JComboBox<>();
        intakeComboBox = new JComboBox<>();
        recipeFoodComboBox = new JComboBox<>();
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        textArea1.setLineWrap(true);
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);

        String defaultText = "Food: \n\nTotal Calories: 0\nTotal Fat: 0\nTotal Carbs: 0\nTotal Protein: 0";
        logTextArea.setText(defaultText);

        weightTextField = new JTextField(10);
        desiredCaloriesTextField = new JTextField(10);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane logScrollPane = new JScrollPane(logTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane recipeScrollPane = new JScrollPane(textArea1);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane exerciseScrollPane = new JScrollPane(exerciseDetailsTextArea);
        exerciseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("New Basic Food:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Name:"), gbc);

        nameTextField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Calories:"), gbc);

        caloriesTextField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(caloriesTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Fat:"), gbc);

        fatTextField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(fatTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Carbs:"), gbc);

        carbsTextField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(carbsTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Protein:"), gbc);

        proteinTextField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(proteinTextField, gbc);

        makeBasicFoodButton = new JButton("Make Basic Food");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(makeBasicFoodButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Your Weight:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(weightTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("What did you eat:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(intakeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("When did you eat:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(datePicker, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Your Daily Intake:"), gbc);

        addToDailyIntakeButton = new JButton("Add to Daily Intake");
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(addToDailyIntakeButton, gbc);

        saveIntakeButton = new JButton("Save Intake");
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(saveIntakeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(logScrollPane, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Select Exercise:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 13;
        panel.add(exerciseComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        panel.add(new JLabel("Exercise Details:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 14;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(exerciseScrollPane, gbc);


        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("New Recipe:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Recipe Name:"), gbc);

        recipeNameTextField = new JTextField(10);
        gbc.gridx = 4;
        gbc.gridy = 3;
        panel.add(recipeNameTextField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Select to add:"), gbc);

        gbc.gridx = 4;
        gbc.gridy = 4;
        panel.add(recipeFoodComboBox, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Food Count:"), gbc);

        recipeFoodCountTextField = new JTextField(10);
        gbc.gridx = 4;
        gbc.gridy = 5;
        panel.add(recipeFoodCountTextField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 6;
        panel.add(new JLabel("Recipe Contents:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(recipeScrollPane, gbc);

        addNewFoodButton = new JButton("Add To recipe");
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(addNewFoodButton, gbc);

        makeRecipeButton = new JButton("Make Recipe");
        gbc.gridx = 3;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(makeRecipeButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Select your food:"), gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;

        panel.add(comboBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(scrollPane, gbc);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void updateExerciseDetails(String details) {
        exerciseDetails.setText(details);
    }

    public UtilDateModel getModel() {
        return model;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getCaloriesTextField() {
        return caloriesTextField;
    }

    public JTextField getFatTextField() {
        return fatTextField;
    }

    public JTextField getCarbsTextField() {
        return carbsTextField;
    }

    public JTextField getProteinTextField() {
        return proteinTextField;
    }

    public JTextField getRecipeNameTextField() {
        return recipeNameTextField;
    }

    public JTextField getRecipeFoodCountTextField() {
        return recipeFoodCountTextField;
    }

    public JTextField getWeightTextField() {
        return weightTextField;
    }

    public JComboBox<String> getIntakeComboBox() {
        return intakeComboBox;
    }

    public JDatePickerImpl getDatePicker() {
        return datePicker;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTextArea getLogTextArea() {
        return logTextArea;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public JButton getMakeBasicFoodButton() {
        return makeBasicFoodButton;
    }

    public JButton getMakeRecipeButton() {
        return makeRecipeButton;
    }

    public JButton getAddNewFoodButton() {
        return addNewFoodButton;
    }

    public JComboBox<String> getRecipeFoodComboBox() {
        return recipeFoodComboBox;
    }
    
    public JComboBox<String> getExerciseComboBox() {
    return exerciseComboBox;
}

public JTextArea getExerciseDetailsTextArea() {
    return exerciseDetailsTextArea;
}




}
