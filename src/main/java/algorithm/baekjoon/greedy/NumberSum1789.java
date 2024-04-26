package algorithm.baekjoon.greedy;

import java.util.Scanner;

public class NumberSum1789 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long s = sc.nextLong();

        int count = 0;
        long sum = 0;
        for(int i=1; i<s; i++){
            if(sum + i > s) break;
            else {
                sum += i;
                count++;
            }
        }
        if(s == 1) System.out.print("1");
        else System.out.print(count);
    }
}
