package algorithm.implement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GroupWordChecker1316 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int count = Integer.parseInt(sc.nextLine());
        String[] input = new String[count];

        for(int i=0; i<input.length; i++)
            input[i] = sc.nextLine();

        int groupWordCount = 0;

        for(int i=0; i < input.length; i++){
            ArrayList<Character> existCharList = new ArrayList();
            char privious = ' ';
            int stringlength = input[i].length();

            for(int j=0; j < stringlength; j++){
                char present = input[i].charAt(j);
                if( privious != present ){
                    if(existCharList.contains(present)) break;
                    else existCharList.add(present);
                    privious = present;
                }
                if(j == stringlength-1) groupWordCount++;
            }
        }
        System.out.println(groupWordCount);
    }
}
