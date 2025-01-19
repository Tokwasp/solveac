package algorithm.baekjoon.twopointer;

import java.io.*;
import java.util.stream.Stream;

public class Water2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int right = input.length -1;
        int differ = Integer.MAX_VALUE;
        int[] resultArr = new int[2];

        while(left < right){
            int result = input[left] + input[right];

            if(Math.abs(result) < Math.abs(differ)){
                resultArr[0] = input[left];
                resultArr[1] = input[right];
                differ = Math.abs(result);
            }

            if(result < 0){
                left++;
            } else if (result > 0){
                right--;
            } else{
                break;
            }
        }
        System.out.print(resultArr[0] + " " + resultArr[1]);
    }
}