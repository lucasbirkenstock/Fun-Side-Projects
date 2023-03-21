package veggies;

import java.io.Serializable;

public class Carrots extends Vegetable implements Serializable {
    public Carrots(String hardizone) {
        mName = "Carrots";
        mSunRequirement = "Full Sun";
        mSupports = "NA";
        mAphids = "Vulnerable";
        mBeetles = "NA";
        mNematodes = "Vulnerable";
        mNitrogen = "Normal";
        mWorms = "Vulnerable";
        mLeafMiners = "NA";
        mSlugsSnails = "Vulnerable";
        mFliesMaggots = "Vulnerable";
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
            return "August";
        else if (hardinessZone.equals("5"))
            return "August";
        else if (hardinessZone.equals("6"))
            return "September";
        else if (hardinessZone.equals("7"))
            return "September";
        else if (hardinessZone.equals("8"))
            return "September";
        else if (hardinessZone.equals("9"))
            return "October";
        else if (hardinessZone.equals("10"))
            return "November";
        else // If not selected
            return "";
    }
}
