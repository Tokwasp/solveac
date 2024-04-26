package algorithm.baekjoon.implement;

import java.util.Arrays;
import java.util.Scanner;

public class Big7568 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int[] key = new int[number];
        int[] weight = new int[number];
        int[] rank = new int[number];


        //덩치등수배열 1로 초기화
        Arrays.fill(rank , 1);
        StringBuilder sb= new StringBuilder();

        //키 몸무게 받기
        for(int i=0; i<number; i++) {
            key[i] = sc.nextInt();
            weight[i] = sc.nextInt();
        }

        // i = 자신 j = 비교할대상
        for(int i=0; i < number;i++) {
            for (int j = 0; j < number; j++) {
                //자신이 아니면 비교
                if (i != j) {
                    if (key[i] < key[j] && weight[i] < weight[j]) rank[i]++;
                }
            }
        }
        for(int i=0; i<rank.length; i++) sb.append(rank[i]).append(" ");
        System.out.printf("%s",sb);
    }
}
