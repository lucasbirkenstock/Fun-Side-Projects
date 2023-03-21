package com.example.workoutmaker;

public class Exercise implements Comparable{
    private String mName;
    private boolean mCompound; // Compounds should be listed first on plan
    private Enum<MuscleGroup>[] mPrimaryWorked; // Some exercises hit certain muscles harder. Used for weighting. Enums used for simplicity
    private Enum<MuscleGroup>[] mSecondaryWorked;

    // Constructor
    public Exercise(String name, boolean compound, Enum<MuscleGroup>[] primaryWorked, Enum<MuscleGroup>[] secondaryWorked) {
        mName = name;
        mCompound = compound;
        mPrimaryWorked = primaryWorked;
        mSecondaryWorked = secondaryWorked;
    }

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public int compareTo(Object o) {
        // First, sort by compounds. Then, sort by name
        int compoundComp = Boolean.compare(mCompound, ((Exercise) o).mCompound);
        if (compoundComp ==0)
            return this.mName.compareTo(((Exercise) o).mName);
        else if (compoundComp < 0)
            return 1;
        else if (compoundComp > 0)
            return -1;
        else
            return 0; // never reached
    }

    public boolean isCompound() {
        return mCompound;
    }

    public Enum<MuscleGroup>[] getPrimaryWorked() {
        return mPrimaryWorked;
    }

    public Enum<MuscleGroup>[] getSecondaryWorked() {
        return mSecondaryWorked;
    }

    public String getName() {
        return mName;
    }

    public void modifyName() {
        if (mCompound)
            mName = mName + " 3 x 5";
        else
            mName = mName + " 3 x 12";
    }
}
