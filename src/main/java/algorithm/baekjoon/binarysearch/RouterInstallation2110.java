package algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RouterInstallation2110 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int low = 1;
        int high = arr[N-1] - arr[0] + 1;

        while(low < high){
            int mid = (low + high) / 2;

            if(installPossible(mid) < C){
                high = mid;
            }
            else{
                low = mid + 1;
            }
        }
        System.out.println(low-1);
    }

    static int installPossible(int dist){
        int count = 1;
        int last = arr[0];

        for(int i = 1; i < arr.length; i++){
            if(arr[i] - last >= dist){
                count++;
                last = arr[i];
            }
        }
        return count;
    }
}