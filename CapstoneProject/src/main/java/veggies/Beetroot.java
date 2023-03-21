package veggies;

import java.io.Serializable;

public class Beetroot extends Vegetable implements Serializable {
    public Beetroot(String hardizone) {
        mName = "Beetroot";
        mSunRequirement = "Full Sun";
        mSupports = "NA";
        mAphids = "Vulnerable";
        mBeetles = "Vulnerable";
        mNematodes = "Vulnerable";
        mNitrogen = "NA";
        mWorms = "Vulnerable";
        mLeafMiners = "Vulnerable";
        mSlugsSnails = "NA";
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
            return "July";
        else if (hardinessZone.equals("4"))
            return "May";
        else if (hardinessZone.equals("5"))
            return "May";
        else if (hardinessZone.equals("6"))
            return "May";
        else if (hardinessZone.equals("7"))
            return "April";
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
