package implement;

import java.io.*;
import java.util.*;

public class WordReverse17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();

        boolean flag = false;
        char ch = ' ';

        Stack st = new Stack();

        for(int i=0; i<input.length(); i++){
            ch = input.charAt(i);
            if(ch == '<') {
                flag = true;
                if(!st.isEmpty()){
                    while(!st.isEmpty()){
                        sb.append(st.pop());
                    }
                }
            }
            if(ch == '>') {
                flag = false;
                sb.append(ch);
                continue;
            }
            if(flag) sb.append(ch);
           else{
               if(ch != ' ') st.push(ch);
               else{
                   while(!st.isEmpty()){
                       sb.append(st.pop());
                   }
                   sb.append(" ");
               }
               if(!st.isEmpty() && i == input.length()-1){
                   while(!st.isEmpty()){
                       sb.append(st.pop());
                   }
                }
             }
        }
        System.out.print(sb);
    }
}
