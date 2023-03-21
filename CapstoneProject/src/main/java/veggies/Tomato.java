package veggies;

import java.io.Serializable;

public class Tomato extends Vegetable implements Serializable {
    public Tomato(String hardizone) {
        mName = "Tomato";
        mSunRequirement = "Full Sun";
        mSupports = "NA";
        mAphids = "Vulnerable";
        mBeetles = "Vulnerable";
        mNematodes = "Vulnerable";
        mNitrogen = "Normal";
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
            return "June";
        else if (hardinessZone.equals("4"))
            return "July";
        else if (hardinessZone.equals("5"))
            return "July";
        else if (hardinessZone.equals("6"))
            return "June";
        else if (hardinessZone.equals("7"))
            return "May";
        else if (hardinessZone.equals("8"))
            return "April";
        else if (hardinessZone.equals("9"))
            return "March";
        else if (hardinessZone.equals("10"))
            return "May";
        else // If not selected
            return "";
    }
}
