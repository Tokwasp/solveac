package algorithm.implement;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Zero10773 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        int sum = 0;

        for(int i=0; i<number; i ++) {
            stack.push(sc.nextInt());
            sum += stack.peek();
            if (stack.peek() == 0) {
                stack.pop();
                sum -= stack.peek();
                stack.pop();
            }
        }
        System.out.println(sum);
    }
}
