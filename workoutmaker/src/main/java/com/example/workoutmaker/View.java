package com.example.workoutmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class View extends Application {
    private Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
        controller = Controller.getInstance();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Model.generateList();
        //System.out.println(Controller.test.toString());

        launch();
    }
}
// TODO figure out how to disable return to menu unless # days selected
// TODO return button saves a copy of both lists, then does generation method OR generates and then saves resulting list before returning