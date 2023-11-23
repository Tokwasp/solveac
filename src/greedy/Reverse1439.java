package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Reverse1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int[] result = new int[2];
        int length = input.length();

        for(int i=0; i<length; i++){
            if(i == length -1){
                if(input.charAt(i) != input.charAt(i-1)) {
                    result[input.charAt(i) - '0']++;
                    result[input.charAt(i-1) - '0']++;
                }
                else
                    result[input.charAt(i) - '0']++;
                break;
            }

            if(input.charAt(i) != input.charAt(i+1)) {
                result[input.charAt(i) - '0']++;
                System.out.println(Arrays.toString(result));;
            }
            else {
                System.out.println(Arrays.toString(result));
                continue;
            }
        }
        System.out.println(Arrays.toString(result));

    }
}
