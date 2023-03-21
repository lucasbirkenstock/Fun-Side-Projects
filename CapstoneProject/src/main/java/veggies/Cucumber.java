package veggies;

import java.io.Serializable;

public class Cucumber extends Vegetable implements Serializable {
    public Cucumber(String hardizone) {
        mName = "Cucumber";
        mSunRequirement = "Full Sun";
        mSupports = "Climber";
        mAphids = "Vulnerable";
        mBeetles = "Vulnerable";
        mNematodes = "NA";
        mNitrogen = "NA";
        mWorms = "Vulnerable";
        mLeafMiners = "Vulnerable";
        mSlugsSnails = "Vulnerable";
        mFliesMaggots = "NA";
        mSpiderMites = "Vulnerable";
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
            return "August";
        else if (hardinessZone.equals("4"))
            return "July";
        else if (hardinessZone.equals("5"))
            return "July";
        else if (hardinessZone.equals("6"))
            return "July";
        else if (hardinessZone.equals("7"))
            return "June";
        else if (hardinessZone.equals("8"))
            return "June";
        else if (hardinessZone.equals("9"))
            return "April";
        else if (hardinessZone.equals("10"))
            return "April";
        else // If not selected
            return "";
    }
}
