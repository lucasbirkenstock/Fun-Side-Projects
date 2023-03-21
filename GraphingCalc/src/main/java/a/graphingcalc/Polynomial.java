package a.graphingcalc;

import java.util.Collections;
import java.util.LinkedList;

public class Polynomial {
    private LinkedList<Term> mList = new LinkedList<>();

    // Empty constructor
    public Polynomial() {
    }

    public void addTerm(Term term) {
        mList.add(term);
        Collections.sort(mList);
    }

    public String toString() {

        String returnstring = "";
        for (int i = 0; i < mList.size(); i++) {
            returnstring += mList.get(i).toString();

            // Add +/-, handle last element case
            if (!(i == mList.size()-1))
                returnstring += (" " + mList.get(i+1).getSign() + " ");
        }
        returnstring = returnstring.replaceAll("  -", " - ");
        return returnstring;
    }

    public void consolidate(){
        for (int i = 0; i < mList.size()-1; i++) {
            for (int j = 0; j < mList.size(); j++) { // For each element
                if((mList.get(i).getPower() == mList.get(j).getPower()) && mList.get(i)!= mList.get(j)) { // if the power of 1st element is same as any second besides itself
                    mList.get(i).setCoefficient(mList.get(i).getCoefficient() + mList.get(j).getCoefficient());
                    mList.remove(j);
                }
            }
        }
    }

    public Polynomial addPolys(Polynomial poly1, Polynomial poly2) { // Whatever poly1 is gets reassigned
        Polynomial sum = new Polynomial();
        for (int i = 0; i < poly1.mList.size(); i++) {
            sum.addTerm(new Term(poly1.mList.get(i).getCoefficient(),poly1.mList.get(i).getPower()));
        }
        for (int i = 0; i < poly2.mList.size(); i++) {
            sum.addTerm(new Term(poly2.mList.get(i).getCoefficient(),poly2.mList.get(i).getPower()));
        }
        sum.consolidate();
        return sum;
    }

    // INNER CLASS - term
    //
    //
    //
    //

    public class Term implements Comparable, Cloneable{
        private int mCoefficient;
        private int mPower;

        public Term(int coefficient, int power) {
            mCoefficient = coefficient;
            mPower = power;
        }



        public String toString() {
            // Coefficient of 1 cases
            if (mCoefficient == 1) {
                if (mPower > 1)
                    return "x^" + mPower;
                else if (mPower == 1)
                    return "x";
                else if (mPower == 0)
                    return "1";
                else
                    return "x^" + mPower;
            }

            // Coefficient of -1 cases
            if (mCoefficient == -1) {
                if (mPower > 1)
                    return "-x^" + mPower;
                else if (mPower == 1)
                    return "-x";
                else if (mPower == 0)
                    return "-1";
                else
                    return "-x^" + mPower;
            }

            // Coeff of 0 cases
            if (mCoefficient == 0)
                return "";

            // Power of 0 cases
            if (mPower == 0)
                return "" + mCoefficient;

            // Power of 1 cases
            if (mPower == 1)
                return "" + mCoefficient + "x";

            // Normal cases
            return mCoefficient + "x^" + mPower; // do " +/- " later
        }

        public int getCoefficient() {
            return mCoefficient;
        }

        public int getPower() {
            return mPower;
        }

        public String getSign() {
            if (mCoefficient < 0)
                return "";
            else
                return "+";
        }

        // Sort based on exponent high to low
        @Override
        public int compareTo(Object o) {
            o = (Term) o;
            if (mPower == 0) {
                return 1;
            } else if (this.mPower > ((Term) o).getPower()) {
                return -1;
            } else if (this.mPower < ((Term) o).getPower()) {
                return 1;
            } else
                return 0;

            // return this.mPower - o.mPower passes the test but does not order things correctly
        }

        public void setPower(int power) {
            mPower = power;
        }

        public void setCoefficient(int coefficient) {
            mCoefficient = coefficient;
        }

        // ******************************************************************
        // **************** Additional methods for test file ****************
        // ******************************************************************
        // Not needed for my driver


        public void setBoth(int a, int b) {
            mCoefficient = a;
            mPower = b;
        }

        // Additional default constructor
        public Term() {
            mCoefficient = 1;
            mPower = 1;
        }

        public Term (Term copy) {
            mCoefficient = copy.getCoefficient();
            mPower = copy.getPower();
        }

        public Term (String s) {
            if (s == "") {
                mCoefficient = 1;
                mPower =1;
            }
            s = s.replace("^-", "NE"); // differentiate negative exponents from negative coefficients
            s = s.replace("-", "+-"); // make - into +- to prepare for splitting by +
            s = s.replace(" ", ""); // remove whitespace
            s = s.replace("NE", "-"); // make negative exponent sign placeholder - again
            s = s.replace("^", ""); // remove exponent sign
            String[] split1 = s.split("\\+"); // split by +

            for (int i = 0; i < split1.length; i++) {
                if (!split1[i].contains("x")) // handle constant terms
                    split1[i] += "x0";

                if (split1[i].charAt(split1[i].length() - 1) == 'x') // handle x power 1 without ^1 entered by user
                    split1[i] += "1";

                int coeff = Integer.parseInt(split1[i].substring(0, split1[i].indexOf('x')));
                int power = Integer.parseInt(split1[i].substring(split1[i].indexOf('x') + 1));
                mCoefficient = coeff;
                mPower = power;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Term term = (Term) o;
            return mCoefficient == term.mCoefficient && mPower == term.mPower;
        }




    }
}
