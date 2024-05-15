package algorithm.swea.d3;

import java.util.Scanner;

public class Power {
    static int total = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i = 1; i <= 10; i++) {
            int testCase = sc.nextInt();
            int n = sc.nextInt();
            int m = sc.nextInt();

            dfs(n,m);
            System.out.println("#" + testCase + " " + total);
            total = 1;
        }
    }

    static void dfs(int n, int count) {

        if(count == 0) return;

        total *= n;

        dfs(n, count - 1);
    }
}
