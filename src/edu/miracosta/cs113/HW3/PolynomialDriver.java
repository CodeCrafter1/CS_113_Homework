package edu.miracosta.cs113.HW3;

public class PolynomialDriver {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        Term t1_p1 = new Term(-2, 3);
        p1.addTerm(t1_p1);
        Term t2_p1 = new Term(-2, 2);
        p1.addTerm(t2_p1);
        Term t3_p1 = new Term(-1, 1);
        p1.addTerm(t3_p1);
        Term t4_p1 = new Term(-8, 0);
        p1.addTerm(t4_p1);
        System.out.println(p1.toString());
        //
        Polynomial p2 = new Polynomial();
        Term t1_p2 = new Term(3, 3);
        p2.addTerm(t1_p2);
        Term t2_p2 = new Term(3, 2);
        p2.addTerm(t2_p2);
        Term t3_p2 = new Term(3, 1);
        p2.addTerm(t3_p2);
        Term t4_p2 = new Term(1, 0);
        p2.addTerm(t4_p2);
        Term t5_p2 = new Term(2, -1);
        p2.addTerm(t5_p2);
        System.out.println(p2.toString());
        System.out.println("--------------------------------------------------------------------");
        Polynomial sum = Polynomial.addPolynomials(p1, p2);
        System.out.println("p1: " + p1.toString());
        System.out.println("p2: " + p2.toString());
        System.out.println("sum: " + sum.toString());
        System.out.println("------------------------------------------------------");

        Polynomial p3 = new Polynomial();
        p3.addTerm("-2x^2^-1x^3^+1+4x^7^");
        System.out.println(p3.toString());

    }

}
