package algorithm.baekjoon.math;

import java.util.Scanner;

public class Decimal1929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m  = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[1000001];

        for(int i=2; i<arr.length; i++) arr[i] = 1;

        for(int i=2; i<arr.length; i++){
            if(arr[i] == 0) continue;
            else {
                for(int j=i*2; j<arr.length; j+=i){
                    arr[j] = 0;
                }
            }
        }
        for(int i=m; i<=n; i++){
            if(arr[i] != 0) System.out.println(i);
        }
    }
}
