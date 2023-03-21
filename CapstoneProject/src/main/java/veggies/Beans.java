package veggies;

import java.io.Serializable;

public class Beans extends Vegetable implements Serializable {
    public Beans(String hardizone) {
        mName = "Beans";
        mSunRequirement = "Full Sun";
        mSupports = "Climber";
        mAphids = "Vulnerable";
        mBeetles = "Vulnerable";
        mNematodes = "Suppress";
        mNitrogen = "Fixer";
        mWorms = "NA";
        mLeafMiners = "Vulnerable";
        mSlugsSnails = "NA";
        mFliesMaggots = "NA";
        mSpiderMites = "Vulnerable";
        mMonth = determinePlantingMonth(hardizone);
    }

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
            return "May";
        else if (hardinessZone.equals("8"))
            return "June";
        else if (hardinessZone.equals("9"))
            return "April";
        else if (hardinessZone.equals("10"))
            return "May";
        else // If not selected
            return "";

    }
}
