package veggies;

import java.io.Serializable;

public class Corn extends Vegetable implements Serializable {
    public Corn(String hardizone) {
        mName = "Corn";
        mSunRequirement = "Full Sun";
        mSupports = "Support";
        mAphids = "Vulnerable";
        mBeetles = "NA";
        mNematodes = "NA";
        mNitrogen = "NA";
        mWorms = "Vulnerable";
        mLeafMiners = "NA";
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
            return "June";
        else // If not selected
            return "";

    }
}
