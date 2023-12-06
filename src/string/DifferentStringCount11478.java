package string;

import java.io.*;
import java.util.*;

public class DifferentStringCount11478 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Set set = new HashSet();
        String st = "";

        for(int i=1; i<=input.length(); i++){
            for(int j=0; j<input.length(); j++){
                if(j+i > input.length()) continue;
                st = input.substring(j,j+i);
                set.add(st);
            }
        }
        System.out.println(set.size());
    }
}
