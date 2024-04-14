package algorithm.math;

import java.util.Scanner;

public class Hansu1065 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;

        for(int i=1; i<=n; i++){
            // 일의자리 십의자리
            if(i / 10 == 0 || i / 100 == 0) {
                count++;
                continue;
            }
            //백의자리
            if(i / 1000 == 0){
                //백의 자리수
                int a = i / 100;
                //십의 자리수
                int b = (i / 10) % 10;
                //일의 자리수
                int c = (i % 10);

                int differAtoB = b - a ;
                int differBtoC = c - b ;
                if(differBtoC == differAtoB) count++;
            }
        }
        System.out.print(count);
    }
}
