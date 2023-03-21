package veggies;

import java.io.Serializable;

public class Spinach extends Vegetable implements Serializable {
    public Spinach(String hardizone) {
        mName = "Spinach";
        mSunRequirement = "Full Sun";
        mSupports = "NA";
        mAphids = "Vulnerable";
        mBeetles = "NA";
        mNematodes = "NA";
        mNitrogen = "User";
        mWorms = "NA";
        mLeafMiners = "Vulnerable";
        mSlugsSnails = "Vulnerable";
        mFliesMaggots = "NA";
        mSpiderMites = "NA";
        mMonth = determinePlantingMonth(hardizone);
    }

    // problem- thinks it is always null
    @Override
    public String determinePlantingMonth(String hardinessZone)
    {
        if (hardinessZone == null) {
            return "null";
        }
        else if (hardinessZone.equals("3"))
            return "July";
        else if (hardinessZone.equals("4"))
            return "May";
        else if (hardinessZone.equals("5"))
            return "May";
        else if (hardinessZone.equals("6"))
            return "May";
        else if (hardinessZone.equals("7"))
            return "May";
        else if (hardinessZone.equals("8"))
            return "April";
        else if (hardinessZone.equals("9"))
            return "March";
        else if (hardinessZone.equals("10"))
            return "February";
        else // If not selected
            return "";
    }
}
