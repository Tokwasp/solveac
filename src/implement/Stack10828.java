package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stack10828 {
    public static void main(String[] args) throws IOException {
        int[] stack = new int[10];
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int location = 0;
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<count; i++) {
            String[] input = br.readLine().split(" ");
            String command = input[0];

            if (command.equals("push")) {
                stack = stackEnsureCapacity(stack, location);
                stack[location] = Integer.parseInt(input[1]);
                location++;
            }

            if (command.equals("pop")) {
                sb.append(pop(stack, location)).append("\n");
                if (pop(stack, location) != -1) {
                    stack[location-1] = 0;
                    location--;
                }
            }

            if (command.equals("size")) sb.append(location).append("\n");

            if (command.equals("empty")) {
                if (location == 0) sb.append("1").append("\n");
                else sb.append("0").append("\n");
            }

            if (command.equals("top")) {
                if (location == 0) sb.append("-1").append("\n");
                else sb.append(stack[location-1]).append("\n");
            }
        }
        System.out.printf("%s",sb);
    }
    static int[] stackEnsureCapacity(int[] stack, int location){
        if(stack.length == location) {
            int[] newStack = new int[stack.length * 2];
            System.arraycopy(stack,0,newStack,0,stack.length);
            return newStack;
        }
        else
            return stack;
    }
    static int pop(int[] stack, int location){
        if(location != 0){
            return stack[location-1];
        }
        else return -1;
    }
}
