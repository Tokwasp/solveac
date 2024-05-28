package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class UnsanCross14888 {
    static int[] arr;
    static int[] operator;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        operator = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < 4; i++){
            int count = Integer.parseInt(st.nextToken());
            operator[i] = count;
        }

        dfs(arr[0],1);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int num, int index){
        if(index == arr.length){
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for(int i = 0; i < 4; i++){
            if(operator[i] > 0){

                operator[i]--;

                switch (i){
                    case 0: dfs(num + arr[index], index + 1); break;
                    case 1: dfs(num - arr[index], index + 1); break;
                    case 2: dfs(num * arr[index], index + 1); break;
                    case 3: dfs(num / arr[index], index + 1); break;
                }

                operator[i]++;
            }
        }
    }
}
