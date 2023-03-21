package a.graphingcalc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
private String mInput = "";
private int mCount = 100;                      // number of x to test
private Point[] thePoints = new Point[mCount]; // creates array of sets of points the size of number of x values to test

private ArrayList theElements = new ArrayList();

// Objects for things in UI, to extract input from.
@FXML
private Label inputLabel = new Label();
@FXML
private TextField maxX = new TextField();
@FXML
private TextField minX = new TextField();
@FXML
private TextField maxY = new TextField();
@FXML
private TextField minY = new TextField();


    private int mMaxX, mMaxY, mMinX , mMinY;

    // TODO IDEA make sqrt input a new popup ************************
    private void getPoints() {
        // Calculate domain and range from user input.
        mMaxX = Integer.parseInt(maxX.getText());
        mMaxY = Integer.parseInt(maxY.getText());
        mMinX = Integer.parseInt(minX.getText());
        mMinY = Integer.parseInt(minY.getText());
        int domain = mMaxX - mMinX;
        int range = mMaxY - mMinY;
        // Step values
        double xStep = domain / mCount;
        double yStep = range / mCount;

        // Prepare to evaluate all points
        int currX = mMinX;
        int pointIndex = 0;

        // For each x value being tested
        for (double i = currX; i < mMaxX; i+= xStep) {

        }



    }



    protected class Point {
        private int x;
        private int y;
        public Point (int xin, int yin) {
            x = xin;
            y= yin;
        }
        public int getX() {return x;}
        public int getY() {return y;}

    }

    @FXML
    public void appendOne(ActionEvent event) throws IOException {
        mInput += "1";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendTwo(ActionEvent event) throws IOException {
        mInput += "2";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendThree(ActionEvent event) throws IOException {
        mInput += "3";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendFour(ActionEvent event) throws IOException {
        mInput += "4";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendFive(ActionEvent event) throws IOException {
        mInput += "5";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendSix(ActionEvent event) throws IOException {
        mInput += "6";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendSeven(ActionEvent event) throws IOException {
        mInput += "7";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendEight(ActionEvent event) throws IOException {
        mInput += "8";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendNine(ActionEvent event) throws IOException {
        mInput += "9";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendZero(ActionEvent event) throws IOException {
        mInput += "0";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendDecimal(ActionEvent event) throws IOException {
        mInput += ".";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendSub(ActionEvent event) throws IOException {
        mInput += "-";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendPlus(ActionEvent event) throws IOException {
        mInput += "+";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendDiv(ActionEvent event) throws IOException {
        mInput += "/";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendMult(ActionEvent event) throws IOException {
        mInput += "*";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendX(ActionEvent event) throws IOException {
        mInput += "x";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendSquare(ActionEvent event) throws IOException {
        mInput += "^2";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendPow(ActionEvent event) throws IOException {
        mInput += "^";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendParO(ActionEvent event) throws IOException {
        mInput += "(";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendParC(ActionEvent event) throws IOException {
        mInput += ")";
        inputLabel.setText(mInput);
    }
    @FXML
    public void appendSqrt(ActionEvent event) throws IOException {
        mInput += "sqrt(";
        inputLabel.setText(mInput);
    }
    @FXML
    public void clearInput(ActionEvent event) throws IOException {
        getPoints();
        mInput = "";
        inputLabel.setText(mInput);
    }

}
