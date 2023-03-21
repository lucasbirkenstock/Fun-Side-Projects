package com.example.capstoneproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import veggies.Vegetable;

public class Controller {


    private static Controller theInstance;
    private ObservableList<Vegetable> mAllVeggiesList;
    private ObservableList<Vegetable> mFilteredAllVeggiesList;
    private ObservableList<Vegetable> mCompanionList;
    private ObservableList<Vegetable> mAvoidList;
    private ObservableList<String> mSavedPairsList;

    // Private empty constructor to prevent instantiation
    private Controller() {};


    // WIP //
    public static Controller getInstance()
    {
        String zone;
        if (theInstance == null)
        {
            theInstance = new Controller();
            theInstance.mAllVeggiesList = Model.populateVeggiesList(View.getZone(View.hardinessCB));
            theInstance.mFilteredAllVeggiesList = FXCollections.observableArrayList();
            theInstance.mCompanionList = FXCollections.observableArrayList();
            theInstance.mAvoidList = FXCollections.observableArrayList();

            if (Model.binaryFileHasData())
                theInstance.mSavedPairsList = Model.populateListFromBinaryFile();
            else
            theInstance.mSavedPairsList = FXCollections.observableArrayList();
        }
        return theInstance;
    }
    // WIP //


    // Fill sun CB
    public ObservableList<String> getSunOptions()
    {
        // Create an empty list
        ObservableList<String> sunops = FXCollections.observableArrayList();

        // Fill the list with options
        sunops.add("Please select the amount of sun");
        sunops.add("Full Sun");
        sunops.add("Partial shade");
        sunops.add("Shade");

        // Return the now full list
        return sunops;
    }

    public ObservableList<String> getHardinessOptions()
    {
        // Create empty list
        ObservableList<String> hardizones = FXCollections.observableArrayList();

        // Fill list with options
        hardizones.add("Please select your hardiness zone");
        hardizones.add("3");
        hardizones.add("4");
        hardizones.add("5");
        hardizones.add("6");
        hardizones.add("7");
        hardizones.add("8");
        hardizones.add("9");
        hardizones.add("10");

        // Return list
        return hardizones;
    }

    public ObservableList<String> getMonth()
    {
        // Create empty list
        ObservableList<String> months = FXCollections.observableArrayList();

        // Fill list with options
        months.add("Please select your desired sowing month");
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        // Return list
        return months;
    }

    // These parameters should be passed in from combo box
    public ObservableList<Vegetable> filter(String sun, String month, String zone)
    {
        // Clear filtered list
        mFilteredAllVeggiesList.clear();

        mAllVeggiesList = Model.populateVeggiesList(zone);
        // For each loop, add to list if 3 conditions are met
        for (Vegetable v : mAllVeggiesList) {
            if (v.getSunRequirement().equals(sun) && v.getMonth().equals(month))
            {

                mFilteredAllVeggiesList.add(v);
            }

        }

        return mFilteredAllVeggiesList;
    }

    public void resetMag() {
        for (Vegetable v : mFilteredAllVeggiesList)
            v.setMagnitude(0.0);
    }

    // Takes in new selected vegetable
    public void cSuggestAvoid(Vegetable newselection) {

        // Clear first to avoid duplicates
        mAvoidList.clear();
        mCompanionList.clear();

        // For each vegetable in list
        for (Vegetable v : mAllVeggiesList)
        {
            // Do not add to either list if same type of vegetable

            if (v.getName().equals(newselection.getName()))
                continue;

            // Each vegetable gets their mMagnitude modified
            v.calculateMagnitude(newselection);



            if (v.getMagnitude() >= 0)
                mCompanionList.add(v);
            else if (v.getMagnitude() < 0)
            {
                mAvoidList.add(v);
            }


        }
        // sort by magnitude method
    }

    public ObservableList<Vegetable> getCompanionList() {
        FXCollections.sort(mCompanionList);
        return mCompanionList;
    }
    public ObservableList<Vegetable> getAvoidList() {
        FXCollections.sort(mAvoidList);
        return mAvoidList;
    }

    public void saveData() {
        System.out.println("savedata being invoked");
        Model.writeDataToBinaryFile(mSavedPairsList);
    }

    public ObservableList<String> getAllSaved() {
        return mSavedPairsList;
    }
}
