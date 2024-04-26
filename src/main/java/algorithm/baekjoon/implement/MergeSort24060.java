package algorithm.baekjoon.implement;

import java.io.*;
import java.util.stream.Stream;

public class MergeSort24060 {
    static int[] arr;
    static int[] temp;
    static int target;
    static StringBuilder sb;
    static int k = 1;

    static void mergeSort(int start, int end){
        if(start<end){
            int mid = (start + end) / 2;
            int q = mid + 1;
            int t = start;

            mergeSort(start,q-1);
            mergeSort(q,end);
            int copyStart = start;
            int copyEnd = end;

            while(start <= mid && q <= end){
                if(arr[start]< arr[q]) {
                    temp[t++] = arr[start++];
                    if(k == target) sb.append(temp[t-1]);
                }
                else {
                    temp[t++] = arr[q++];
                    if(k == target) sb.append(temp[t-1]);
                }
                k++;
            }

            while(start <= mid){
                temp[t++] = arr[start++];
                if(k == target) sb.append(temp[t-1]);
                k++;
            }

            while(q <= end){
                temp[t++] = arr[q++];
                if(k == target) sb.append(temp[t-1]);
                k++;
            }

            for(int i=0; i<=end; i++) if(temp[i] !=0) arr[i] = temp[i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int arrLength = Integer.parseInt(input[0]);
        target = Integer.parseInt(input[1]);

        arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        temp = new int[arrLength];
        sb = new StringBuilder();
        mergeSort(0,arr.length-1);
        if(target > k-1) sb.append("-1");
        System.out.print(sb);
    }
}
