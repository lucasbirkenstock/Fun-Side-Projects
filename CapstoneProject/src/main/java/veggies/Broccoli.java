package veggies;

import java.io.Serializable;

public class Broccoli extends Vegetable implements Serializable {
    public Broccoli(String hardizone) {
        mName = "Broccoli";
        mSunRequirement = "Full Sun";
        mSupports = "NA";
        mAphids = "Vulnerable";
        mBeetles = "Vulnerable";
        mNematodes = "NA";
        mNitrogen = "User";
        mWorms = "Vulnerable";
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
            return "August";
        else if (hardinessZone.equals("5"))
            return "August";
        else if (hardinessZone.equals("6"))
            return "September";
        else if (hardinessZone.equals("7"))
            return "September";
        else if (hardinessZone.equals("8"))
            return "October";
        else if (hardinessZone.equals("9"))
            return "October";
        else if (hardinessZone.equals("10"))
            return "November";
        else // If not selected
            return "";

    }
}
