package algorithm.baekjoon.datastructure;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class RankFiveNumber17299 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        int[] count = new int[1000001];
        int[] ans = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
            count[data[i]]++;
        }

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < N; i++){
            while(!stack.isEmpty() && count[data[stack.peek()]] < count[data[i]]){
                ans[stack.pop()] = data[i];
            }
            stack.add(i);
        }

        while(!stack.isEmpty())
            ans[stack.pop()] = -1;

        for(int i = 0; i<N; i++)
            sb.append(ans[i]).append(" ");

        System.out.println(sb);
    }
}
