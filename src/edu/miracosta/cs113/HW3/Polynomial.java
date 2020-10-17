package edu.miracosta.cs113.HW3;

import java.util.LinkedList;
import java.util.ListIterator;


public class Polynomial {
	 private LinkedList<Term> polynomial;//built-in LL

	    public Polynomial() {//default constructor
	        create();//call create - private since called in the constructor
	    }

	    private void create() {
	        polynomial = new LinkedList<Term>();//empty poly
	    }

	    public Polynomial(Polynomial p) {//copy constructor
	        create();
	        int len = p.polynomial.size();
	        for (int i = 0; i < len; i++) {
	            polynomial.addLast(p.polynomial.get(i));//add top to bottom of the list in order, always adding at the bottom
	        }
	    }

	    public void clear() {
	        polynomial.clear();
	    }

	    public Term getTerm(int i) {//returns the term at index i
	        int size = this.polynomial.size();
	        int j = 0;
	        while (j < size) {
	            if (i == j) {
	                return this.polynomial.get(i);//get gets the element/node of the list, which is a term
	            }
	            j++;
	        }
	        return null;//if the index was not found, return null
	    }

	    //if exponent matches what is in the list, add the coefficients
	    public void addTerm(String termsString) {//currently the string MUST have the format: 3x^2^+2x+1 with no spaces. or spaces between
	        //as 3x^2^ + 2x + 1 - OK.
	        String termsStringNew = termsString.replaceAll(" ", "");//get rid of all spaces
	        String[] termSplit = termsStringNew.split("[+-]");//gives the terms without signs. Non-empty ones are the terms without signs.
	        int terms = termSplit.length;
	        int[] coeff = new int[terms];
	        int[] exp = new int[terms];
	        parse(termsStringNew, terms, coeff, exp);//splits the string to get the coeff and exp for each term if exists, or get just the coeff when no exp.
	        //
	        //check if the term is already in the list - then do not add it.
	        for (int i = 0; i < terms; i++) {
	            Term term = new Term(coeff[i], exp[i]);//make terms from the coeff and exp arrays.
	            if (this.polynomial.isEmpty()) {
	                this.polynomial.addLast(term);
	            } else {
	                int size = this.polynomial.size();
	                int j = 0;
	                boolean inserted = false;
	                while (j < size) {
	                    if (term.compareTo(this.polynomial.get(j)) == 0) {//if the exponents match, add the coeff's
	                        this.polynomial.get(j).setCoefficient(term.getCoefficient() + this.polynomial.get(j).getCoefficient());
	                        inserted = true;
	                        break;
	                    }
	                    if (term.compareTo(this.polynomial.get(j)) == 1) {//if the new term has a larger exponent, insert it before the current.
	                        this.polynomial.add(j, term);
	                        inserted = true;
	                        break;
	                    }
	                    j++;
	                }//end while
	                if (inserted == false) {//if the new term is smaller than all of the others in the list, add it at the end.
	                    this.polynomial.addLast(term);
	                }
	            }
	        }//end for
	        //
	    }

	    public void addTerm(Term term) {//adds 1 term
	        for (int i = 0; i < 1; i++) { //this for loop runs once. Only one term is allowed, so the loop runs 1 time. Copied from above where many terms ok.
	            if (this.polynomial.isEmpty()) {
	                this.polynomial.addLast(term);
	            } else {
	                int size = this.polynomial.size();
	                int j = 0;
	                boolean inserted = false;
	                while (j < size) {
	                    if (term.compareTo(this.polynomial.get(j)) == 0) {
	                        this.polynomial.get(j).setCoefficient(term.getCoefficient() + this.polynomial.get(j).getCoefficient());//this adds the coeff's
	                        inserted = true;
	                        break;
	                    }
	                    if (term.compareTo(this.polynomial.get(j)) == 1) {
	                        this.polynomial.add(j, term);
	                        inserted = true;
	                        break;
	                    }
	                    j++;
	                }//end while
	                if (inserted == false) {
	                    this.polynomial.addLast(term);
	                }
	            }
	        }//end for
	        //
	    }

	    //add 2 polys
	    public static Polynomial addPolynomials(final Polynomial p1, final Polynomial p2) {//static so that it can be called without an object - use class name
	        Polynomial sum = new Polynomial();//make an empty poly called sum
	        ListIterator it1 = p1.polynomial.listIterator(0);
	        while (it1.hasNext()) {
	            sum.addTerm((Term) it1.next());
	        }
	        ListIterator it2 = p2.polynomial.listIterator(0);
	        while (it2.hasNext()) {
	            sum.addTerm((Term) it2.next());
	        }

	        return sum;

	    }

	    @Override
	    public String toString() {
	        String s = "";
	        for (int i = 0; i < polynomial.size(); i++) {
	            if (i == 0) {//don't add anything if the first except the coeff
	                s += polynomial.get(i).getCoefficient();
	            } else {
	                if (polynomial.get(i).getCoefficient() > 0) {//if not first and > 0, add a + sign
	                    s += "+";
	                    s += polynomial.get(i).getCoefficient();
	                } else {
	                    s += polynomial.get(i).getCoefficient();//if negative, use the - sign of the negative and just add the coeff
	                }
	            }
	            if (polynomial.get(i).getExponent() > 1 || polynomial.get(i).getExponent() < 0) {//if the exp is 2 or more, put a caret after x
	                s += "x^";
	            }
	            if (polynomial.get(i).getExponent() == 1) {//if the exp is 1, just put an x
	                s += "x";
	            }
	            if (polynomial.get(i).getExponent() > 1) {//if the exp is > 1, get the exp.  Otherwise the exp is not used.
	                s += polynomial.get(i).getExponent();
	            }
	            if (polynomial.get(i).getExponent() < 0) {//if the exp is > 1, get the exp.  Otherwise the exp is not used.
	                s += polynomial.get(i).getExponent();
	            }
	        }
	        return s;
	    }

	    private void parse(String s, int terms, int[] coeff, int[] exp) {
	        String[] termSplit = s.split("[+-]");//non-empty strings give terms without signs. Used to make the size of x and caret.
	        boolean[] x = new boolean[terms];
	        boolean[] caret = new boolean[terms];
	        int k = 0;
	        for (int i = 0; i < terms; i++) {//x is an array that says if there is an x in the term. caret says if there is a caret in the term - exp > 1, i.e.
	            if (!termSplit[i].isEmpty()) {
	                if (termSplit[i].contains("x")) {
	                    x[k] = true;
	                } else {
	                    x[k] = false;
	                }
	                if (termSplit[i].contains("^")) {
	                    caret[k] = true;
	                } else {
	                    caret[k] = false;
	                }
	                //System.out.println("k= " + k + " sss[i] is = " + termSplit[i] + " x " + x[k] + " caret " + caret[k]);
	                k++;
	            }
	        }
	        String[] coeffSplit = s.split("[+x^]");//this split gives each coeff and exp if exp > 1. It gives the coeff only if exp <= 1.
	        k = 0;
	        int i = 0;
	        while (i < coeffSplit.length) {
	            if (!coeffSplit[i].isEmpty()) {
	                if (x[k] == false && caret[k] == false) {//this is the number only with no x
	                    coeff[k] = Integer.parseInt(coeffSplit[i]);
	                    exp[k] = 0;
	                    //System.out.println("k= " + k + " coeff[k] is = " + coeff[k] + " exp[k] is = " + exp[k]);
	                    k++;
	                    i++;
	                } else if (x[k] == true && caret[k] == false) {//this is the term with a coeff and x and no exp
	                    coeff[k] = Integer.parseInt(coeffSplit[i]);
	                    exp[k] = 1;
	                    //System.out.println("k= " + k + " coeff[k] is = " + coeff[k] + " exp[k] is = " + exp[k]);
	                    k++;
	                    i++;
	                } else {//here there is a coeff and an exp
	                    coeff[k] = Integer.parseInt(coeffSplit[i]);
	                    i++;
	                    if (coeffSplit[i].isEmpty()) {
	                        i++;
	                    }
	                    exp[k] = Integer.parseInt(coeffSplit[i]);
	                    i++;
	                    //System.out.println("k= " + k + " coeff[k] is = " + coeff[k] + " exp[k] is = " + exp[k]);
	                    k++;
	                }
	            } else {
	                i++;
	            }
	        }
	    }

}
