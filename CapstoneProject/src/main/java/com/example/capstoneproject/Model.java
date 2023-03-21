package com.example.capstoneproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import veggies.*;

import java.io.*;

public class Model {

    private static final String DATA_FILE = "plants.txt";

    // This method creates a new list of all vegetable objects, which will have their planting months updated accurately. It does not filter them out or sort them.

    public static ObservableList<Vegetable> populateVeggiesList(String zone) {

        ObservableList<Vegetable> allVeggies = FXCollections.observableArrayList();

        allVeggies.add(new Tomato(zone));
        allVeggies.add(new Beans(zone));
        allVeggies.add(new Beetroot(zone));
        allVeggies.add(new Broccoli(zone));
        allVeggies.add(new BrusselsSprouts(zone));
        allVeggies.add(new Cabbage(zone));
        allVeggies.add(new Carrots(zone));
        allVeggies.add(new Cauliflower(zone));
        allVeggies.add(new Cucumber(zone));
        allVeggies.add(new Kale(zone));
        allVeggies.add(new Lettuce(zone));
        allVeggies.add(new Peas(zone));
        allVeggies.add(new Marigold(zone));
        allVeggies.add(new Corn(zone));
        allVeggies.add(new Peppers(zone));
        allVeggies.add(new Spinach(zone));
        allVeggies.add(new Squash(zone));
        allVeggies.add(new Potato(zone));

        return allVeggies;

    }

    public static final File BINARY_FILE = new File("VegetablePairings.dat");
    public static boolean binaryFileHasData()
    {
        return (BINARY_FILE.exists() && BINARY_FILE.length() > 1L);
    }

    public static ObservableList<String> populateListFromBinaryFile()
    {
        ObservableList<String> veggiesSerializable = FXCollections.observableArrayList();

        if (binaryFileHasData())
        {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(BINARY_FILE));

                //Done: Create a temp array of Entry Software Job objects to read from the binary file
                //Done: Initialize the temp array from the binary file reader.
                String[] tempArray = (String[]) fileReader.readObject();

                //Done: Add the temp array to the collection of all entry software jobs (list)
                veggiesSerializable.addAll(tempArray);

                //Done: Close the binary file reader.
                fileReader.close();
            } catch (IOException | ClassNotFoundException e)
            {
                //Done: If an exception occurs, print the message "Error opening binary file for reading."
                System.err.println("Error opening binary file for reading.");
            }
        }

        return veggiesSerializable;
    }

    public static boolean writeDataToBinaryFile(ObservableList<String> veggiesSerializable)
    {
        //Done: Create a temp array of Entry Software Job objects to read from binary file (length should match list size)
        String[] tempArray = new String[veggiesSerializable.size()];

        //Done: Loop through the temp array and initialize each element to the corresponding one in the list
        for (int i = 0; i < tempArray.length ; i++) {
            tempArray[i] = veggiesSerializable.get(i);
        }

        try {
            //Done: Instantiate an ObjectOutputStream reference to the binary file for writing
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(BINARY_FILE));

            //Done: Write the temp array object to the binary file writer
            fileWriter.writeObject(tempArray);

            //Done: Close the binary file writer and return true.
            fileWriter.close();
            return true;

        } catch (IOException e) {
            //Done: If an exception occurs, print its message and return false.
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }
}
