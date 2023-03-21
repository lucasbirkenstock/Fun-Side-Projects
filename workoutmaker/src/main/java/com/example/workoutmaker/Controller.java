package com.example.workoutmaker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Stage mStage;
    private Scene mScene;
    private Parent mRoot;
    private Exercise selectedAdd;
    private Exercise selectedRemove;
    private static int daysSelected;
    private ArrayList<Exercise> storedList = new ArrayList<>();
    private static ArrayList<Exercise> generatedProgram = new ArrayList<>(); // For fullbody
    private static ArrayList<Exercise> generatedUpper = new ArrayList<>(); // For UL
    private static ArrayList<Exercise> generatedLower = new ArrayList<>(); // For UL
    private Object[] generatedList;
    private double chestW = 0, shoulderW = 0, bisW = 0, trisW = 0, backW = 0, quadsW = 0, hamsW = 0, calfW =0, coreW = 0, latsW = 0, glutesW = 0;
    Integer[] days = {1,2,3,4,5,6,7};


    @FXML
    private ListView<Exercise> selectLifts = new ListView<>(); // initial list
    @FXML
    private ListView<Exercise> stored = new ListView<>(); // User selected exercises
    @FXML
    private  ListView<Exercise> fullbodyLIST = new ListView<>(); // User selected exercises
    @FXML
    private ComboBox<Integer> daysAvailableBox = new ComboBox<Integer>(); // combobox for # days to lift per week
    @FXML
    private Label mainpageError = new Label();
    @FXML
    private Label builderError = new Label();
    @FXML
    private Button revealButtonFB = new Button();
    @FXML
    private ListView<Exercise> lowerlistUL = new ListView<>();
    @FXML
    private ListView<Exercise> upperlistUL = new ListView<>();

    // Populates the listview which displays exercises for a user to add before generating plan
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectLifts.getItems().addAll(Model.generateList());
        selectLifts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Exercise>() {
            @Override
            public void changed(ObservableValue<? extends Exercise> observableValue, Exercise exercise, Exercise t1) {
                // Adds listener to list with exercises to choose from. Updates selectedAdd to store selected exercise
                selectedAdd = selectLifts.getSelectionModel().getSelectedItem();
            }
        });
        stored.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Exercise>() {
            @Override
            public void changed(ObservableValue<? extends Exercise> observableValue, Exercise exercise, Exercise t1) {
                // Adds listener to added/chosen exercises. Updates selectedRemove to store selected exercise.
                selectedRemove = stored.getSelectionModel().getSelectedItem();
            }
        });
        daysAvailableBox.getItems().addAll(days);
    }

    // Clicking "get started" moves to other scene
    @FXML
    public void switchToBuilder(ActionEvent event) throws IOException {
        mRoot = FXMLLoader.load(getClass().getResource("builder.fxml"));
        mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mScene = new Scene(mRoot);
        mStage.setScene(mScene);
        mStage.show();
    }
    @FXML
    public void refreshFB(ActionEvent event) throws IOException {
        if (fullbodyLIST.getItems().isEmpty()) {
            for (Exercise e : generatedProgram) {
                e.modifyName();
            }
            fullbodyLIST.getItems().addAll(generatedProgram);
            fullbodyLIST.setVisible(true);
            revealButtonFB.setVisible(false);
        }
    }

    @FXML
    public void switchToFB(ActionEvent event) throws IOException {
        mRoot = FXMLLoader.load(getClass().getResource("fullbody.fxml"));
        mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mScene = new Scene(mRoot);
        mStage.setScene(mScene);
        mStage.show();
    }

    @FXML
    public void switchToUL(ActionEvent event) throws IOException {
        mRoot = FXMLLoader.load(getClass().getResource("upperlower.fxml"));
        mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mScene = new Scene(mRoot);
        mStage.setScene(mScene);
        mStage.show();
    }

    @FXML
    public void viewResults(ActionEvent event) throws IOException {
        if (daysSelected == 0)
        mainpageError.setVisible(true);
        else if (daysSelected <= 3)
            switchToFB(event);
        else if (daysSelected == 4)
            switchToUL(event);
        //else {}

    }
    // Clicking button to return to menu switches scenes
    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        // If no # of days is selected, code breaks. Handles this case. Do not generate a plan if no day # selected.
        if (daysAvailableBox.getValue() != null) {
            daysSelected = daysAvailableBox.getValue();
            generatePlan();
            fullbodyLIST.getItems().addAll(generatedProgram);
            mRoot = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
            mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            mScene = new Scene(mRoot);
            mStage.setScene(mScene);
            mStage.show();
        } else // Makes error message visible if user does not select day #
            builderError.setVisible(true);
    }

    @FXML
    public void switchToMainNoGen(ActionEvent event) throws IOException {
        // Switches scene back to menu
        mRoot = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        mScene = new Scene(mRoot);
        mStage.setScene(mScene);
        mStage.show();
    }

    // Adds selected item to list when + is pressed
    @FXML
    public void addExercise(ActionEvent event)  {
        if (selectedAdd != null) {
            stored.getItems().add(selectedAdd);
            storedList.add(selectedAdd);
            selectLifts.getItems().remove(selectedAdd);
        }
    }

    @FXML
    public void removeExercise(ActionEvent event) {
        if (selectedRemove != null) {
            selectLifts.getItems().add(selectedRemove);
            storedList.remove(selectedRemove);
            stored.getItems().remove(selectedRemove);
        }
    }

    // For use in controller instantiation in other classes, to use its methods.
    private static Controller theInstance;

    // Empty constructor
    public Controller(){};

    // Instantiate controller instance, fill observablelists in old project TODO consider moving test1 shit here
    // TODO potentially pointless
    public static Controller getInstance() {
        if (theInstance == null) {
            theInstance = new Controller(); // instantiate it
            // make observablelists a field of theinstance
        }
        return theInstance;
    }

    public void generatePlan() {
        generatedList = storedList.toArray();
        generatedProgram = storedList;

        // Generate different plans based on days selected

        switch (daysSelected) {
            case 1,2,3:
                // full body

                // **** Calculates weights of exercises added by user
                for (Object o : generatedList) {
                    int genPlanIterator =0;

                    for (Enum e: ((Exercise) o).getPrimaryWorked()){
                        // Add weights for user preselected exercises, primary
                        if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.CHEST){
                            chestW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.SHOULDERS) {
                           shoulderW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.BIS) {
                            bisW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.TRIS) {
                            trisW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.BACK) {
                            backW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.QUADS) {
                            quadsW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.HAMS) {
                            hamsW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.CALF) {
                            calfW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.CORE) {
                            coreW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.LATS) {
                            latsW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.GLUTES) {
                            glutesW++;
                            genPlanIterator++;
                        }
                    }
                    // Reset iterator variable to count secondary muscles worked
                    genPlanIterator = 0;

                    // Add weights for user preselected exercises, secondary worked
                    for (Enum e: ((Exercise) o).getSecondaryWorked()){
                        if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.CHEST){
                            chestW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.SHOULDERS) {
                            shoulderW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.BIS) {
                            bisW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.TRIS) {
                            trisW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.BACK) {
                            backW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.QUADS) {
                            quadsW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.HAMS) {
                            hamsW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.CALF) {
                            calfW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.CORE) {
                            coreW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.LATS) {
                            latsW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.GLUTES) {
                            glutesW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.NONE) {
                            // Do nothing
                        }
                    }
                }

                ObservableList<Exercise> selectLiftsObservable= selectLifts.getItems();

                // After accounting for all exercises added by user, use loop to add required exercises until each @ 1.5+
                do {
                    // While at least 1 weight is below 1.5:
                    // Check each individual muscle group for weight. If < 1.5, iterate through selectlifts.
                    // First, check if object in selectlifts already exists in generatedlist (redundant?). If not, Check if primary exercise is the same muscle group.
                    // If yes, increment -muscle- by +1. Add that exercise to generatedlist. Remove it from selectlifts.

                    for (Object o : selectLiftsObservable) { // For every object in the original list users choose exercises from. Duplicates are impossible, as adding an exercise removes it.
                        // For every exercise remaining in the right hand list
                        int genPlanIterator2 = 0;
                        for (Enum e: ((Exercise) o).getPrimaryWorked()){
                            if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.CHEST && chestW < 1.5) {
                                chestW++;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.SHOULDERS && shoulderW < 1.5) {
                                shoulderW++;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.BIS && bisW < 1.5) {
                                bisW++;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.TRIS && trisW < 1.5) {
                                trisW++;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.BACK && backW < 1.5) {
                                backW++;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.QUADS && quadsW < 1.5) {
                                quadsW++;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.HAMS && hamsW < 1.5) {
                                hamsW++;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.CALF && calfW < 1.5) {
                                calfW++;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.CORE && coreW < 1.5) {
                                coreW++;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.LATS && latsW < 1.5) {
                                latsW++;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.GLUTES && glutesW < 1.5) {
                                glutesW++;
                                genPlanIterator2++;
                                addto(o);
                            }
                        }
                        genPlanIterator2 = 0;
                        for (Enum e: ((Exercise) o).getSecondaryWorked()){
                            if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.CHEST && chestW < 1.5){
                                chestW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.SHOULDERS && shoulderW < 1.5) {
                                shoulderW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.BIS && bisW < 1.5) {
                                bisW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.TRIS && trisW < 1.5) {
                                trisW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.BACK && backW < 1.5) {
                                backW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.QUADS && quadsW < 1.5) {
                                quadsW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.HAMS && hamsW < 1.5) {
                                hamsW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.CALF && calfW < 1.5) {
                                calfW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.CORE && coreW < 1.5) {
                                coreW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.LATS && latsW < 1.5) {
                                latsW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.GLUTES && glutesW < 1.5) {
                                glutesW += 0.5;
                                genPlanIterator2++;
                                addto(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.NONE) {
                                // Do nothing
                            }
                        }
                    }
                } while (fullLoop());

                break;
            case 4:
                // upper lower
                // **** Calculates weights of exercises added by user (same as fullbody)
                for (Object o : generatedList) {
                    // Use same algorithm for calculating user weights, but also add user selected to EITHER upper or lower list
                    int genPlanIterator =0;

                    for (Enum e: ((Exercise) o).getPrimaryWorked()){
                        // Decide whether an exercise is upper/lower based on the first primary muscle group associated with it,
                        // because of cases like deadlifts, which has both upper and lower groups as primary

                        if (((Exercise) o).getPrimaryWorked()[0] == MuscleGroup.CHEST || ((Exercise) o).getPrimaryWorked()[0] == MuscleGroup.SHOULDERS ||
                                ((Exercise) o).getPrimaryWorked()[0] == MuscleGroup.BIS || ((Exercise) o).getPrimaryWorked()[0] == MuscleGroup.TRIS ||
                                ((Exercise) o).getPrimaryWorked()[0] == MuscleGroup.BACK) {
                            if(!upperlistUL.getItems().contains(o))
                                addtoUpper(o);
                        } else {
                            if (!lowerlistUL.getItems().contains(o))
                                addtoLower(o);
                        }

                        // Add weights for user preselected exercises, primary
                        if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.CHEST){
                            chestW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.SHOULDERS) {
                            shoulderW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.BIS) {
                            bisW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.TRIS) {
                            trisW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.BACK) {
                            backW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.QUADS) {
                            quadsW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.HAMS) {
                            hamsW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.CALF) {
                            calfW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.CORE) {
                            coreW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.LATS) {
                            latsW++;
                            genPlanIterator++;
                        } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator] == MuscleGroup.GLUTES) {
                            glutesW++;
                            genPlanIterator++;
                        }
                    }
                    // Reset iterator variable to count secondary muscles worked
                    genPlanIterator = 0;

                    // Add weights for user preselected exercises, secondary worked
                    for (Enum e: ((Exercise) o).getSecondaryWorked()){
                        if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.CHEST){
                            chestW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.SHOULDERS) {
                            shoulderW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.BIS) {
                            bisW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.TRIS) {
                            trisW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.BACK) {
                            backW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.QUADS) {
                            quadsW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.HAMS) {
                            hamsW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.CALF) {
                            calfW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.CORE) {
                            coreW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.LATS) {
                            latsW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.GLUTES) {
                            glutesW += 0.5;
                            genPlanIterator++;
                        } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator] == MuscleGroup.NONE) {
                            // Do nothing
                        }
                    }
                }


                // After accounting for all exercises added by user, use loop to add required exercises until each @ 1.5+
                do {
                    // While at least 1 weight is below 1.5:
                    // Check each individual muscle group for weight. If < 1.5, iterate through selectlifts.
                    // First, check if object in selectlifts already exists in generatedlist (redundant?). If not, Check if primary exercise is the same muscle group.
                    // If yes, increment -muscle- by +1. Add that exercise to generatedlist. Remove it from selectlifts.
                    selectLiftsObservable = selectLifts.getItems();
                    for (Object o : selectLiftsObservable) { // For every object in the original list users choose exercises from. Duplicates are impossible, as adding an exercise removes it.
                        // For every exercise remaining in the right hand list
                        int genPlanIterator2 = 0;
                        for (Enum e: ((Exercise) o).getPrimaryWorked()){
                            if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.CHEST && chestW < 1.5) {
                                chestW++;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.SHOULDERS && shoulderW < 1.5) {
                                shoulderW++;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.BIS && bisW < 1.5) {
                                bisW++;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.TRIS && trisW < 1.5) {
                                trisW++;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.BACK && backW < 1.5) {
                                backW++;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.QUADS && quadsW < 1.5) {
                                quadsW++;
                                genPlanIterator2++;
                                addtoLower(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.HAMS && hamsW < 1.5) {
                                hamsW++;
                                genPlanIterator2++;
                                addtoLower(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.CALF && calfW < 1.5) {
                                calfW++;
                                genPlanIterator2++;
                                addtoLower(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.CORE && coreW < 1.5) {
                                coreW++;
                                genPlanIterator2++;
                                addtoLower(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.LATS && latsW < 1.5) {
                                latsW++;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getPrimaryWorked()[genPlanIterator2] == MuscleGroup.GLUTES && glutesW < 1.5) {
                                glutesW++;
                                genPlanIterator2++;
                                addtoLower(o);
                            }
                        }
                        genPlanIterator2 = 0;
                        for (Enum e: ((Exercise) o).getSecondaryWorked()){
                            if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.CHEST && chestW < 1.5){
                                chestW += 0.5;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.SHOULDERS && shoulderW < 1.5) {
                                shoulderW += 0.5;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.BIS && bisW < 1.5) {
                                bisW += 0.5;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.TRIS && trisW < 1.5) {
                                trisW += 0.5;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.BACK && backW < 1.5) {
                                backW += 0.5;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.QUADS && quadsW < 1.5) {
                                quadsW += 0.5;
                                genPlanIterator2++;
                                addtoLower(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.HAMS && hamsW < 1.5) {
                                hamsW += 0.5;
                                genPlanIterator2++;
                                addtoLower(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.CALF && calfW < 1.5) {
                                calfW += 0.5;
                                genPlanIterator2++;
                                addtoLower(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.CORE && coreW < 1.5) {
                                coreW += 0.5;
                                genPlanIterator2++;
                                addtoLower(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.LATS && latsW < 1.5) {
                                latsW += 0.5;
                                genPlanIterator2++;
                                addtoUpper(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.GLUTES && glutesW < 1.5) {
                                glutesW += 0.5;
                                genPlanIterator2++;
                                addtoLower(o);
                            } else if (((Exercise) o).getSecondaryWorked()[genPlanIterator2] == MuscleGroup.NONE) {
                                // Do nothing
                            }
                        }
                    }
                } while (fullLoop());
                break;
            case 5:
                // ???
                break;
            case 6,7:
                // PPL
                break;
        }
    }

    public void addto(Object o) {
        if (!(generatedProgram.contains(o)) && daysSelected <=3)
            generatedProgram.add((Exercise) o);
    }

    public void addtoUpper(Object o) {
        if (!generatedUpper.contains(o))
            generatedUpper.add((Exercise) o);
    }
    public void addtoLower(Object o) {
        if (!generatedLower.contains(o))
            generatedLower.add((Exercise) o);
    }

    private boolean fullLoop() {
            // Each muscle group needs a weight of 1.5 - hitting each group with 3+ secondary or 1 primary + 1 secondary
            // Return true if any muscle group has weight < 1.5
        if ((chestW < 1.5 && shoulderW < 1.5 && bisW < 1.5 && trisW < 1.5 && backW < 1.5 && quadsW < 1.5 && hamsW < 1.5 && calfW < 1.5 && coreW < 1.5 && latsW < 1.5 && glutesW < 1.5))
            return true;
        else
            return false;
    }

    private boolean upperLoop() {
        if ((chestW < 1.5 && shoulderW < 1.5 && bisW < 1.5 && trisW < 1.5 && backW < 1.5 &&  latsW < 1.5))
            return true;
        else
            return false;
    }

    private boolean lowerLoop() {
        if ((quadsW < 1.5 && hamsW < 1.5 && calfW < 1.5 && coreW < 1.5 && glutesW < 1.5))
            return true;
        else
            return false;
    }

}
