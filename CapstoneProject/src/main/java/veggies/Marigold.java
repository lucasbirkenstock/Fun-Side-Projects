package veggies;

import java.io.Serializable;

public class Marigold extends Vegetable implements Serializable {
    public Marigold(String hardizone) {
        mName = "Marigold";
        mSunRequirement = "Full Sun";
        mSupports = "NA";
        mAphids = "Suppress";
        mBeetles = "NA";
        mNematodes = "Suppress";
        mNitrogen = "NA";
        mWorms = "Suppress";
        mLeafMiners = "NA";
        mSlugsSnails = "Suppress";
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
            return "March";
        else if (hardinessZone.equals("9"))
            return "February";
        else if (hardinessZone.equals("10"))
            return "January";
        else // If not selected
            return "";

    }
}
