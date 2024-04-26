package algorithm.baekjoon.implement;

import java.util.Scanner;

public class Fractions1193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int remainder = sc.nextInt();
        int count = 1;
        int numerator = 0, denominator = 0;

        //몇번째 지그재그 몇번째 자리인지 확인
        while(remainder - count > 0){
            remainder -= count;
            count++;
        }
        //짝수 배치일경우
        if(count % 2 == 0){
            numerator =  1 + (remainder-1);
            denominator = count - (remainder-1);
        }
        // 홀수 배치의 경우
        else{
            numerator = count - (remainder-1);
            denominator = 1 + (remainder-1);
        }
        System.out.println(String.valueOf(numerator+"/"+denominator));
    }
}
