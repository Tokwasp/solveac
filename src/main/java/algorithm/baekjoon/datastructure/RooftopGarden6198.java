package algorithm.baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class RooftopGarden6198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        long count = 0;
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            while(!stack.isEmpty() && stack.peekFirst() <= num){
                stack.pop();
            }
            count += stack.size();
            stack.push(num);
        }
        System.out.println(count);
    }
}