package algorithm.baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact {
    private static boolean pass = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            pass = true;
            solve(input);

            if (!pass) {
                sb.append("No").append("\n");
            } else {
                sb.append("YES").append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void solve(String input) {
        while(pass) {
            if (input.startsWith("100")) {
                input = input.substring(3);
                int nextOneIndex = input.indexOf("1");
                if (nextOneIndex == -1) {
                    pass = false;
                    break;
                }
                input = input.substring(nextOneIndex);
                int nextZeroIndex = input.indexOf("0");
                if (nextZeroIndex == -1) {
                    break;
                }
                input = input.substring(nextZeroIndex - 1);
            } else if (input.startsWith("01")) {
                input = input.substring(2);
            } else {
                pass = false;
            }
        }
    }
}