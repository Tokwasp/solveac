package algorithm.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * dp[i] -> i번째 num 끝나는 가장 긴 부분 수열
 *
 * ex) 3 1 2 5
 *
 * dp[1] = 1;
 *
 * dp[2]
 * arr[2] = 1
 * 앞부분 (j = 1; j < 2;) 자신의 부분 수열이 없으 므로 dp[2] = 1
 *
 * dp[3]
 * arr[3] = 2
 * 앞부분 (j = 1; j < 3;) 1은 부분 수열이 된다.
 * dp[3] = Math.max(dp[3], dp[2] + 1)
 * dp[3] = 1 (초기값), dp[2] = 1
 * dp[3] = 2
 *
 * dp[4] (j = 1; j < 4;) 3,1,2 는 5의 부분 수열이 된다.
 * dp[4] = Math.max(1, dp[1] + 1)
 * dp[4] = Math.max(2, dp[2] + 1)
 * dp[4] = Math.max(2, dp[3] + 1)
 * dp[4] = 3
 *
 * 참고 : dp 너무 어렵다.
 **/

public class LongestSubsequence11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int index = 1;

        while(st.hasMoreTokens()){
            arr[index] = Integer.parseInt(st.nextToken());
            dp[index] = 1;
            index++;
        }

        int maxLength = 1;

        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j < i; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }
        System.out.println(maxLength);
    }
}
