package algorithm.baekjoon.math;

import java.io.*;
import java.util.StringTokenizer;

public class WaterBottle1052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int result = solve(n, k);
        System.out.println(result);
    }

    private static int solve(int n, int k) {
        int ans = 0;
        while(Integer.bitCount(n) > k){
            ans++;
            n++;
        }
        return ans;
    }
}