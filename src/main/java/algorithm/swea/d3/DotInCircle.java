package algorithm.swea.d3;

import java.util.Scanner;

/*
    문제 풀이:

    풀이 방법 : 구현

    시간 복잡도 : 1초에 100개 -> 100만
    x, y >= 0 일 경우, 200 * 200 = 4만
 */
public class DotInCircle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int testCase = sc.nextInt();

        for(int t = 1; t <= testCase; t++) {
            int N = sc.nextInt();

            int pluscount = 0;
            int minusCount = 0;

            for(int x = 0; x <= N; x++) {
                int remain = (N * N) - (x * x);

                for(int y = 0; y <= N; y++) {

                    boolean passCheck = remain - (y * y) >= 0;

                    if(passCheck) {
                        pluscount++;
                        if(x != 0) minusCount++;
                        if(y != 0) minusCount++;
                        if(x != 0 && y != 0) minusCount++;
                    }

                }
            }

            int total = pluscount + minusCount;
            String st = "#" + t + " " + total;
            sb.append(st).append("\n");
        }
        System.out.print(sb);
    }
}