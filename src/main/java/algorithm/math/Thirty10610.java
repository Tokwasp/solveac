package algorithm.math;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Thirty10610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        boolean determine = false;
        String st = " ";
        StringBuilder sb = new StringBuilder();

        //0이 있어야 30의 배수가 되므로 검사한다.
        for(int i=0; i<input.length; i++){
            st = input[i];
            if(st.equals("0")) {
                determine = true;
                break;
            }
        }

        //각자리 수의 합이 3의 배수여야 한다.
        if(determine){
            Integer[] arr = Stream.of(input).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            Arrays.sort(arr, Comparator.reverseOrder());

            int count = 0;
            for(int i:arr) count += i;
            if(count % 3 == 0){
                for(int i: arr) sb.append(i);
            }
            else sb.append(-1);
        }
        else sb.append(-1);
        System.out.print(sb);
    }
}
