package algorithm.baekjoon.dp;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class LIS14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            list.add(new ArrayList<>());
        }

        int maxSize = 0;
        int maxIndex = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j]){
                    if(list.get(i).size() < list.get(j).size() + 1){
                        list.set(i, new ArrayList<>(list.get(j)));
                    }
                }
            }
            list.get(i).add(arr[i]);
            if(list.get(i).size() > maxSize){
                maxSize = list.get(i).size();
                maxIndex = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> resultList = list.get(maxIndex);
        sb.append(resultList.size()).append("\n");
        for (int value : resultList) {
            sb.append(value).append(" ");
        }
        System.out.print(sb);
    }
}