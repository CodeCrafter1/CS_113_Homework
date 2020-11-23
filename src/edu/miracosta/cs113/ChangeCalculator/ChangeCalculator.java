package edu.miracosta.cs113.ChangeCalculator;

public class ChangeCalculator {

    public static int[] coins = {25, 10, 5, 1};
    public static int amount = 10;
    public static int num = 0;
    public static int[][] temp = new int[1000][coins.length];
    public static int amountOriginal = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        amountOriginal = amount;
        System.out.println(ChangeCalculator(amount, 0));
        for (int i = 0; i < 1000; i++) {
            boolean prnt = false;
            for (int k = 0; k < coins.length; k++) {
                if (temp[i][k] != 0) {
                    prnt = true;
                }
            }
            if (prnt == true) {
                for (int j = 0; j < coins.length; j++) {
                    System.out.print(temp[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }

    public static int ChangeCalculator(int amount, int currentCoin) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0) {
            return 0;
        }
        int nCombos = 0;

        for (int coin = currentCoin; coin < coins.length; coin++) {

            int j = ChangeCalculator(amount - coins[coin], coin);
            if (j > 0) {
                temp[num][coin] += j;
                nCombos += j;
            }
            int sum = temp[num][0] * 25 + temp[num][1] * 10 + temp[num][2] * 5 + temp[num][3] * 1;
            if (sum == amountOriginal) {
                num++;
            }
        }
        System.out.println(" nCombos " + nCombos + " ");
        return nCombos;
    }

}
