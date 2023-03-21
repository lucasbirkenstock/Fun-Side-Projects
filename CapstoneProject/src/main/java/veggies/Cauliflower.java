package veggies;

import java.io.Serializable;

public class Cauliflower extends Vegetable implements Serializable {
    public Cauliflower(String hardizone) {
        mName = "Cauliflower";
        mSunRequirement = "Full Sun";
        mSupports = "NA";
        mAphids = "Vulnerable";
        mBeetles = "NA";
        mNematodes = "NA";
        mNitrogen = "User";
        mWorms = "Vulnerable";
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
            return "May";
        else if (hardinessZone.equals("7"))
            return "April";
        else if (hardinessZone.equals("8"))
            return "March";
        else if (hardinessZone.equals("9"))
            return "March";
        else if (hardinessZone.equals("10"))
            return "November";
        else // If not selected
            return "";
    }
}
