package edu.miracosta.cs113.HW3;

public class Term implements Comparable<Term> {

    private int coefficient;
    private int exponent;

    Term(int coefficient, int exponent) {//only one constructor
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    @Override
    public int compareTo(Term term) {//comparison is based only on exponents. The largest exp must be first. The terms in the list are sorted by exp.
        if (this.exponent > term.getExponent()) {
            return 1;
        } else if (this.exponent == term.getExponent()) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * @return the coefficient
     */
    public int getCoefficient() {
        return coefficient;
    }

    /**
     * @param coefficient the coefficient to set
     */
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * @return the exponent
     */
    public int getExponent() {
        return exponent;
    }

    /**
     * @param exponent the exponent to set
     */
    public void setExponent(int exponent) {
        this.exponent = exponent;
    }
}
