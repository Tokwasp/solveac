package algorithm.baekjoon.implement;

import java.io.*;

public class AAndB12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder t = new StringBuilder(br.readLine());

        while(t.length() != s.length()){
            char tLastChar = t.charAt(t.length() - 1);
            t.deleteCharAt(t.length() - 1);

            if(tLastChar == 'B'){
                t = t.reverse();
            }
        }
        if(t.toString().equals(s)) System.out.println(1);
        else System.out.println(0);
    }
}