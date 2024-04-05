package algorithm.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class CroatianAlphabet2941 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] croatianAlphabet = new String[]{"c=","c-","dz=","d-","lj","nj","s=","z="};
        ArrayList<String> croatianlist = new ArrayList<>();
        Collections.addAll(croatianlist,croatianAlphabet);
        String privious = "";
        String threeWord = "";
        int count = 0;

        for(int i=0; i<input.length(); i++){
            String present = String.valueOf(input.charAt(i));
            threeWord += present;
            count++;
            if (threeWord.length() == 3){
                if(croatianlist.contains(threeWord)) {
                    count -= 2;
                    threeWord = "";
                    privious = "";
                    continue;
                }
                else threeWord = threeWord.substring(1);
            }
           if(croatianlist.contains(privious + present)) {
                privious = "";
                threeWord = "";
                count--;
                continue;
            }
            privious = present;
        }
        System.out.println(count);
    }
}

