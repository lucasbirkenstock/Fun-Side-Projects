package com.example.capstoneproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import veggies.Tomato;
import veggies.Vegetable;

import java.io.File;

public class View extends Application{


    // Things to put in grid
    private ComboBox<String> sunCB = new ComboBox<>();
    private ComboBox<String> monthCB = new ComboBox<>();
    protected static ComboBox<String> hardinessCB = new ComboBox<>();
    private ImageView hardinessMapIV = new ImageView();
    private ImageView garden1IV = new ImageView();
    private ImageView chickenIV = new ImageView();
    private ImageView garden2IV = new ImageView();
    ListView<Vegetable> avoidVeggiesLV = new ListView<>();
    ListView<Vegetable> suggestedVeggiesLV = new ListView<>();
    ListView<Vegetable> pickVegetableLV = new ListView<>();
    ListView<String> savedVegLV = new ListView<>();
    private Button removeButton = new Button("- Remove Pair");
    private Button addButton = new Button("+ Select Companion");
    private Button addButton2 = new Button("+ Select Plant");
    private Button pairButton = new Button("+ Add Pair");
    private ObservableList<String> savedList;
    private String first, second, selected;
    // Things to put in grid

    private Controller controller;

    @Override
    public void start(Stage primarystage) throws Exception {

        // Instantiate controller to use its methods
        controller = Controller.getInstance();

        // Set options for combo boxes
        sunCB.setItems(controller.getSunOptions());
        hardinessCB.setItems(controller.getHardinessOptions());
        monthCB.setItems(controller.getMonth());

        // Create the window and whatnot
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(5));

        // Add to pane - combo boxes
        pane.add(sunCB, 2, 1);
        sunCB.getSelectionModel().selectFirst();
        sunCB.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> filter2()));

        pane.add(hardinessCB,0,1);
        hardinessCB.getSelectionModel().selectFirst();
        hardinessCB.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> filter2()));

        pane.add(monthCB,1,1);
        monthCB.getSelectionModel().selectFirst();
        monthCB.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> filter2()));


        // Add to pane - IMAGES
        File file = new File("res/Zone-Map.jpg");
        File img2 = new File("res/garden1.jpg");
        File img3 = new File("res/chickens1.jpg");
        File img4 = new File("res/coolgarden.jpg");
        Image map = new Image(file.toURI().toString());
        Image garden1 = new Image(img2.toURI().toString());
        Image chickens = new Image(img3.toURI().toString());
        Image garden2 = new Image(img4.toURI().toString());
        garden1IV.setImage(garden1);
        chickenIV.setImage(chickens);
        garden2IV.setImage(garden2);

        garden1IV.setFitWidth(300);
        chickenIV.setFitWidth(310);
        garden1IV.setFitHeight(400);
        chickenIV.setFitHeight(400);
        hardinessMapIV.setImage((map));
        hardinessMapIV.setFitHeight(400);
        hardinessMapIV.setFitHeight(400);
        garden2IV.setFitWidth(300);
        garden2IV.setFitHeight(400);
        pane.add(hardinessMapIV,1,2);
        pane.add(garden1IV,0,2);
        //pane.add(garden2IV,2,2);
        pane.add(chickenIV,2,2);

        savedVegLV.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> updateSelected(newValue)));


        // Add to pane - labels
        pane.add(new Label("Good companions"), 0, 3);
        pane.add(new Label("Choose a plant to find companions for!"), 1, 3);
        pane.add(new Label("Avoid planting these together!"), 2, 3);
        pane.add(new Label("Your hardiness zone"), 0, 0);
        pane.add(new Label("When you want to plant"), 1, 0);
        pane.add(new Label("Amount of sun"), 2, 0);
        pane.add(new Label("Saved pairs"), 4, 3);

        // Add to pane - list views
        pane.add(suggestedVeggiesLV, 0, 4);
        pane.add(avoidVeggiesLV, 2, 4);
        pane.add(pickVegetableLV, 1, 4);
        pane.add(savedVegLV, 4, 4);

        // Add buttons
        pane.add(removeButton,4,5);
        pane.add(addButton,0,5);
        pane.add(addButton2,1,5);
        pane.add(pairButton,2,5);
        pairButton.setOnAction(event -> addPair(first, second));
        addButton.setOnAction(event -> getCompanion());
        addButton2.setOnAction(event -> getPlant());
        removeButton.setOnAction(event -> removePair());

        // Associate event of list view to call displayimage
        pickVegetableLV.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> suggestAvoid(newVal));

        // Define size and visibility
        Scene scene = new Scene(pane, 1280, 720);
        primarystage.setTitle("Birkenstock Companion Cropper");
        primarystage.setScene(scene);
        primarystage.show();

        // Saved stuff
         savedList = controller.getAllSaved();
         savedVegLV.setItems(savedList);
    }

    public static String getZone(ComboBox<String> a) {
        return a.getValue();
    }

    // Private filter method for combo boxes to call
    // This method populates the bottom middle listview
    private void filter2() {

        String month, sun, zone;
        zone = hardinessCB.getValue();
        month = monthCB.getValue();
        sun = sunCB.getValue();

        // Call the controller method
        pickVegetableLV.setItems(controller.filter(sun,month,zone));
    }

    private void suggestAvoid(Vegetable newselection) {
        if (newselection != null) {
            controller.cSuggestAvoid(newselection);
            avoidVeggiesLV.setItems(controller.getAvoidList());
            suggestedVeggiesLV.setItems(controller.getCompanionList());
            // unused controller.resetMag();

        }
    }

    private void getPlant(){
        first = pickVegetableLV.getSelectionModel().getSelectedItem().getName();
    }

    private void getCompanion() {
        second = suggestedVeggiesLV.getSelectionModel().getSelectedItem().getName();
    }

    private void addPair(String first, String second) {

        String asdf;
        asdf = first + ", " + second;
        savedList.add(asdf);
        controller.getInstance().saveData();
    }

    private void removePair() {
        savedList.remove(savedVegLV.getSelectionModel().getSelectedItem());
        updateDisplay();
        controller.getInstance().saveData();
    }

    private void updateDisplay()
    {
        // Done: Uncomment after implementing the Comparable interface in EntrySoftwareJob.java
        FXCollections.sort(savedList);

        savedVegLV.refresh();
    }

    private void updateSelected(String newValue) {
        selected = newValue;
    }
}
