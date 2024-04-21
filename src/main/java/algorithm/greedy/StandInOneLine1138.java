package algorithm.greedy;

import java.io.*;
import java.util.stream.Stream;

public class StandInOneLine1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] arr = new int[n];

        for(int i=input.length-1; i>=0; i--){
            int init = n - (i + 1);
            int index = input[i];
            if(init == index) arr[index] = i+1;
            else {
                int move = init - index;
                System.arraycopy(arr, index, arr, index + 1, move);
                arr[index] = i+1;
            }
//            for (int h : arr) {
//                System.out.print(h);
//            }
//            System.out.println();
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
