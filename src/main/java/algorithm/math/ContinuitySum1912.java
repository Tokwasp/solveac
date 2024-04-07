package algorithm.math;

import java.io.*;
import java.util.stream.Stream;

public class ContinuitySum1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[n];

        int continuity = 0;
        for(int i=0; i<arr.length; i++){
            int num = arr[i];
            if (continuity + num < 0 ) continuity = 0;
            else continuity += num;

            if(i == 0) dp[0] = num;
            if(i > 0) {
                if (continuity > dp[i - 1]) dp[i] = continuity;
                else dp[i] = dp[i - 1];
                if(continuity > dp[i - 1] && num < 0) dp[i] = Math.max(dp[i-1],num);
            }
        }
        System.out.print(dp[n-1]);
    }
}
