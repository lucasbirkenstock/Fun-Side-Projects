package veggies;

import java.io.Serializable;

public class Peas extends Vegetable implements Serializable {
    public Peas(String hardizone) {
        mName = "Peas";
        mSunRequirement = "Full Sun";
        mSupports = "Climber";
        mAphids = "Vulnerable";
        mBeetles = "NA";
        mNematodes = "NA";
        mNitrogen = "Fixer";
        mWorms = "Vulnerable";
        mLeafMiners = "Vulnerable";
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
            return "August";
        else if (hardinessZone.equals("4"))
            return "May";
        else if (hardinessZone.equals("5"))
            return "May";
        else if (hardinessZone.equals("6"))
            return "April";
        else if (hardinessZone.equals("7"))
            return "April";
        else if (hardinessZone.equals("8"))
            return "April";
        else if (hardinessZone.equals("9"))
            return "February";
        else if (hardinessZone.equals("10"))
            return "February";
        else // If not selected
            return "";
    }
}
