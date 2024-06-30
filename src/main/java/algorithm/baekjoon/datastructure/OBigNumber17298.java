package algorithm.baekjoon.datastructure;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class OBigNumber17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[N];
        Arrays.fill(ans, -1);

        for(int i = 0; i < N; i++){
            while(!stack.isEmpty() && arr[stack.peek()] < arr[i]){
                ans[stack.pop()] = arr[i];
            }
            stack.add(i);
        }

        for (int i : ans) {
            sb.append(i + " ");
        }
        System.out.println(sb);
    }
}
