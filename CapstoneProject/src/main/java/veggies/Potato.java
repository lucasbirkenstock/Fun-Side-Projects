package veggies;

import java.io.Serializable;

public class Potato extends Vegetable implements Serializable {
    public Potato(String hardizone) {
        mName = "Potato";
        mSunRequirement = "Full Sun";
        mSupports = "NA";
        mAphids = "Vulnerable";
        mBeetles = "Vulnerable";
        mNematodes = "NA";
        mNitrogen = "Normal";
        mWorms = "Vulnerable";
        mLeafMiners = "NA";
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
            return "May";
        else if (hardinessZone.equals("4"))
            return "April";
        else if (hardinessZone.equals("5"))
            return "April";
        else if (hardinessZone.equals("6"))
            return "April";
        else if (hardinessZone.equals("7"))
            return "March";
        else if (hardinessZone.equals("8"))
            return "February";
        else if (hardinessZone.equals("9"))
            return "January";
        else if (hardinessZone.equals("10"))
            return "January";
        else // If not selected
            return "";
    }
}
