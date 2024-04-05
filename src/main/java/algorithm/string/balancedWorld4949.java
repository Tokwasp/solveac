package algorithm.string;

import java.io.*;
import java.util.*;

public class balancedWorld4949 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();

        while(!input.equals(".")){
            boolean test = true;
            Stack<Character> stack = new Stack<>();

            for(int i=0; i<input.length(); i++){

                char ch = input.charAt(i);

                if(ch == '(' || ch == '[') stack.push(ch);

                if(ch == ')'){
                    if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
                    else{
                        test = false;
                        break;
                    }
                }
                if(ch == ']'){
                    if(!stack.isEmpty() && stack.peek() == '[') stack.pop();
                    else{
                        test = false;
                        break;
                    }
                }
            }
            if(!test || !stack.isEmpty()) sb.append("no").append("\n");
            else sb.append("yes").append("\n");
            input = br.readLine();
        }
        System.out.print(sb);
    }
}
