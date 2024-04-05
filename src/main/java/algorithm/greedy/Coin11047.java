package algorithm.greedy;

import java.util.Scanner;

public class Coin11047 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int charge = sc.nextInt();
        int[] moneyArr = new int[number];
        int count = 0, quotient = 0;

        for(int i=0; i<number; i++) moneyArr[i] = sc.nextInt();

        while(charge != 0){
            for(int i=number-1; i>=0; i--){
                quotient = charge / moneyArr[i];
                if (quotient >= 1) {
                    charge = charge - (quotient * moneyArr[i]);
                    count += quotient;
                }
            }
        }
        System.out.println(count);
    }
}
