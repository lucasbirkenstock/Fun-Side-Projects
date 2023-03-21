package veggies;

import java.io.Serializable;

public class Vegetable implements Serializable, Comparable {

    // Fields
    protected String mName;
    protected String mSunRequirement;
    protected String mMonth;
    protected String mNitrogen;
    protected String mSupports;
    protected String mAphids;
    protected String mNematodes;
    protected String mBeetles;
    protected String mWorms;
    protected String mLeafMiners;
    protected String mSlugsSnails;
    protected String mFliesMaggots;
    protected String mSpiderMites;
    protected double mMagnitude;
    // Fields


    public Vegetable() {}

    @Override
    public String toString() {
        String output;
        output = mName;
        if (mMagnitude != 0.0)
            output += ", magnitude = " + mMagnitude;
        return output;
    }

    // To be overridden
    public String determinePlantingMonth(String hardinessZone)
    {
        return "";
    }

    // Getters and setters
    public String getSunRequirement() {
        return mSunRequirement;
    }
    public String getMonth() {
        return mMonth;
    }
    public double getMagnitude() {
        return mMagnitude;
    }
    public String getName() { return mName; }

    public void setName(String name) {
        mName = name;
    }

    public void calculateMagnitude(Vegetable other) {
        double magnitude = 0;

        if ((this.mAphids.equals("Vulnerable") && other.mAphids.equals("Suppress")) || (other.mAphids.equals("Vulnerable") && this.mAphids.equals("Suppress")))
            magnitude += 1;
        else if (this.mAphids.equals(other.mAphids))
            magnitude =- 0.125;

        if ((this.mSupports.equals("Climber") && other.mSupports.equals("Support")) || (other.mSupports.equals("Climber") && this.mSupports.equals("Support")))
            magnitude++;

        if ((this.mSunRequirement.equals(other.mSunRequirement)))
            magnitude += 0.125;

        if ((this.mBeetles.equals("Vulnerable") && other.mBeetles.equals("Suppress")) || (other.mBeetles.equals("Vulnerable") && this.mBeetles.equals("Suppress")))
            magnitude += 1;
        else if (this.mBeetles.equals("Vulnerable") && other.mBeetles.equals("Vulnerable"))
            magnitude =- 0.125;

        if ((this.mNematodes.equals("Vulnerable") && other.mNematodes.equals("Suppress")) || (other.mNematodes.equals("Vulnerable") && this.mNematodes.equals("Suppress")))
            magnitude += 1;
        else if (this.mNematodes.equals("Vulnerable") && other.mNematodes.equals("Vulnerable"))
            magnitude =- 0.125;

        if ((this.mNitrogen.equals("User") && other.mNitrogen.equals("Fixer")) || (other.mNitrogen.equals("User") && this.mNitrogen.equals("Fixer")))
            magnitude += 1;
        else if (this.mNitrogen.equals("User") && other.mNitrogen.equals("User"))
            magnitude =- 0.125;

        if ((this.mMonth.equals(other.mMonth)))
            magnitude += 2.5;
        else
            magnitude =- 0.25;

        if ((this.mWorms.equals("Vulnerable") && other.mWorms.equals("Suppress")) || (other.mWorms.equals("Vulnerable") && this.mWorms.equals("Suppress")))
            magnitude += 1;
        else if (this.mWorms.equals("Vulnerable") && other.mWorms.equals("Vulnerable"))
            magnitude =- 0.125;

        if ((this.mLeafMiners.equals("Vulnerable") && other.mLeafMiners.equals("Suppress")) || (other.mLeafMiners.equals("Vulnerable") && this.mLeafMiners.equals("Suppress")))
            magnitude += 1;
        else if (this.mLeafMiners.equals("Vulnerable") && other.mLeafMiners.equals("Vulnerable"))
            magnitude =- 0.125;

        if ((this.mSlugsSnails.equals("Vulnerable") && other.mSlugsSnails.equals("Suppress")) || (other.mSlugsSnails.equals("Vulnerable") && this.mSlugsSnails.equals("Suppress")))
            magnitude += 1;
        else if (this.mSlugsSnails.equals("Vulnerable") && other.mSlugsSnails.equals("Vulnerable"))
            magnitude =- 0.125;

        if ((this.mFliesMaggots.equals("Vulnerable") && other.mFliesMaggots.equals("Suppress")) || (other.mFliesMaggots.equals("Vulnerable") && this.mFliesMaggots.equals("Suppress")))
            magnitude += 1;
        else if (this.mFliesMaggots.equals("Vulnerable") && other.mFliesMaggots.equals("Vulnerable"))
            magnitude =- 0.125;

        if ((this.mSpiderMites.equals("Vulnerable") && other.mSpiderMites.equals("Suppress")) || (other.mSpiderMites.equals("Vulnerable") && this.mSpiderMites.equals("Suppress")))
            magnitude += 1;
        else if (this.mSpiderMites.equals("Vulnerable") && other.mSpiderMites.equals("Vulnerable"))
            magnitude =- 0.125;

        mMagnitude = magnitude;
    }

    public void setMagnitude(double magnitude) {
        mMagnitude = magnitude;
    }

    @Override
    public int compareTo(Object o) {

        int magnitudeDiff = Double.compare(mMagnitude, ((Vegetable) o).mMagnitude);
        if (magnitudeDiff == 0)
            return 0;
        else if (magnitudeDiff < 0)
            return 1;
        else if (magnitudeDiff > 0)
            return -1;

        return this.mName.compareTo(((Vegetable) o).mName);
    }
}
