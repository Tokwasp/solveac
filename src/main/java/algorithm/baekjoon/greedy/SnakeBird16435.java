package algorithm.baekjoon.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class SnakeBird16435 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int sankeLength = sc.nextInt();
        int[] fruitHeight = new int[count];

        for (int i=0; i<count; i++)
            fruitHeight[i] = sc.nextInt();

        for(int i=0; i<fruitHeight.length; i++){
            Arrays.sort(fruitHeight);
            if (fruitHeight[i] <= sankeLength){
                sankeLength++;
            }
            else
                break;
        }
        System.out.println(sankeLength);
    }
}
