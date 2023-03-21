package veggies;

import java.io.Serializable;

public class BrusselsSprouts extends Vegetable implements Serializable {
    public BrusselsSprouts(String hardizone) {
        mName = "Brussels Sprouts";
        mSunRequirement = "Full Sun";
        mSupports = "NA";
        mAphids = "Vulnerable";
        mBeetles = "NA";
        mNematodes = "NA";
        mNitrogen = "User";
        mWorms = "NA";
        mLeafMiners = "NA";
        mSlugsSnails = "Vulnerable";
        mFliesMaggots = "Vulnerable";
        mSpiderMites = "NA";
        mMonth = determinePlantingMonth(hardizone);
    }

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
            return "May";
        else if (hardinessZone.equals("8"))
            return "June";
        else if (hardinessZone.equals("9"))
            return "May";
        else if (hardinessZone.equals("10"))
            return "October";
        else // If not selected
            return "";

    }
}
