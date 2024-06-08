package algorithm.baekjoon.binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class CuttingTree2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nAndM = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = nAndM[0]; int M = nAndM[1];

        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //최댓값 찾기
        Arrays.sort(arr);
        int max = Arrays.stream(arr).max().getAsInt();

        int lowHeight = 0;
        int highHeight = max;
        int maxHeight = 0;

        while(lowHeight <= highHeight){

            long sum = 0;
            int midHeight= (lowHeight + highHeight) / 2;

            for(int i = 0; i < arr.length; i++){
                if(midHeight < arr[i]) sum += (arr[i] - midHeight);
            }

            if(sum >= M) {
                maxHeight = Math.max(maxHeight, midHeight);
                lowHeight = midHeight + 1;
            }
            else highHeight = midHeight - 1;

        }

        System.out.println(maxHeight);
    }
}
