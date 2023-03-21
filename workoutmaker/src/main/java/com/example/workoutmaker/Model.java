package com.example.workoutmaker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.Collections;

public class Model {

    public static Exercise[] generateList() {
        Exercise[] allExercises;
        Exercise bbBench = new Exercise("Barbell Bench Press", true, new Enum[]{MuscleGroup.CHEST}, new Enum[]{MuscleGroup.SHOULDERS, MuscleGroup.TRIS});
        Exercise bbOHP = new Exercise("Barbell Overhead Press", true, new Enum[]{MuscleGroup.SHOULDERS}, new Enum[]{MuscleGroup.CHEST, MuscleGroup.TRIS});
        Exercise legPress = new Exercise("Leg Press", true, new Enum[]{MuscleGroup.QUADS}, new Enum[]{MuscleGroup.HAMS, MuscleGroup.GLUTES});
        Exercise bbSquat = new Exercise("Barbell Squat", true, new Enum[]{MuscleGroup.QUADS, MuscleGroup.GLUTES, MuscleGroup.HAMS}, new Enum[]{MuscleGroup.CALF, MuscleGroup.CORE});
        Exercise chestDips = new Exercise("Chest Dips", true, new Enum[]{MuscleGroup.CHEST, MuscleGroup.TRIS}, new Enum[]{MuscleGroup.SHOULDERS});
        Exercise bicepCurl = new Exercise("Bicep Curls", false, new Enum[]{MuscleGroup.BIS}, new Enum[]{MuscleGroup.LATS});
        Exercise pullUp = new Exercise("(Weighted) Pull Ups", true, new Enum[]{MuscleGroup.LATS}, new Enum[]{MuscleGroup.BIS});
        Exercise chinUp = new Exercise("Chin Ups", true, new Enum[]{MuscleGroup.BIS}, new Enum[]{MuscleGroup.LATS});
        Exercise crunch = new Exercise("Crunches", false, new Enum[]{MuscleGroup.CORE}, new Enum[]{MuscleGroup.NONE});
        Exercise latwoodchop = new Exercise("Lateral Wood Chops", false, new Enum[]{MuscleGroup.CORE}, new Enum[]{MuscleGroup.NONE});
        Exercise legExtension = new Exercise("Leg Extension Machine", false, new Enum[]{MuscleGroup.QUADS}, new Enum[]{MuscleGroup.NONE});
        Exercise hamsCurl = new Exercise("Hamstring Curl Machine", false, new Enum[]{MuscleGroup.HAMS}, new Enum[]{MuscleGroup.NONE});
        Exercise calfRaise = new Exercise("Calf Raises", false, new Enum[]{MuscleGroup.CALF}, new Enum[]{MuscleGroup.NONE});
        Exercise cableRow = new Exercise("Cable Rows", false, new Enum[]{MuscleGroup.BACK}, new Enum[]{MuscleGroup.NONE});
        Exercise facePulls = new Exercise("Cable Face Pulls", false, new Enum[]{MuscleGroup.BACK}, new Enum[]{MuscleGroup.SHOULDERS});
        Exercise hipThrust = new Exercise("Hip Thrusts", false, new Enum[]{MuscleGroup.GLUTES}, new Enum[]{MuscleGroup.NONE});
        Exercise skullCrushers = new Exercise("Skullcrushers", false, new Enum[]{MuscleGroup.TRIS}, new Enum[]{MuscleGroup.NONE});
        Exercise dbFlys = new Exercise("Dumbbell Flys", false, new Enum[]{MuscleGroup.SHOULDERS}, new Enum[]{MuscleGroup.NONE});
        Exercise deadlift = new Exercise("Deadlifts", true, new Enum[]{MuscleGroup.GLUTES, MuscleGroup.QUADS, MuscleGroup.HAMS, MuscleGroup.BACK}, new Enum[]{MuscleGroup.CORE, MuscleGroup.CALF});
        allExercises = new Exercise[]{bbBench, bbOHP, bbSquat, chestDips, bicepCurl, pullUp, chinUp, crunch, latwoodchop, legExtension, hamsCurl, calfRaise, cableRow, facePulls, deadlift,
                hipThrust, skullCrushers, dbFlys, legPress};
        return allExercises;
    }

}
