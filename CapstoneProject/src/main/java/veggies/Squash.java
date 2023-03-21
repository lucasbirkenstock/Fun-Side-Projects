package veggies;

import java.io.Serializable;

public class Squash extends Vegetable implements Serializable {
    public Squash(String hardizone) {
        mName = "Squash";
        mSunRequirement = "Full Sun";
        mSupports = "NA";
        mAphids = "Vulnerable";
        mBeetles = "Vulnerable";
        mNematodes = "NA";
        mNitrogen = "Normal";
        mWorms = "NA";
        mLeafMiners = "NA";
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
            return "July";
        else if (hardinessZone.equals("5"))
            return "July";
        else if (hardinessZone.equals("6"))
            return "July";
        else if (hardinessZone.equals("7"))
            return "July";
        else if (hardinessZone.equals("8"))
            return "July";
        else if (hardinessZone.equals("9"))
            return "May";
        else if (hardinessZone.equals("10"))
            return "April";
        else // If not selected
            return "";
    }
}
