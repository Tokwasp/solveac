package algorithm.baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class Top2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<int []> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= N; i++){
            int num = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty() && stack.peek()[1] < num){
                stack.pop();
            }

            if(!stack.isEmpty() && stack.peek()[1] > num){
                sb.append(stack.peek()[0]).append(" ");
            }
            else if(stack.isEmpty()){
                sb.append(0).append(" ");
            }
            stack.add(new int[]{i, num});
        }
        System.out.print(sb);
    }
}