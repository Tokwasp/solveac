package algorithm.baekjoon.datastructure;

import java.io.*;
import java.util.Stack;

public class RooftopGarden6198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        long ans = 0;
        for(int i=0; i<N; i++) {

            int height = Integer.parseInt(br.readLine());

            while(!stack.isEmpty()) {

                if(stack.peek() <= height) {
                    stack.pop();
                }
                else break;
            }
            ans += stack.size();
            stack.push(height);
        }
        bw.write(String.valueOf(ans));
        bw.close();
    }
}