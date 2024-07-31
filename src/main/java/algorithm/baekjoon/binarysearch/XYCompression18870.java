package algorithm.baekjoon.binarysearch;

import java.io.*;
import java.util.*;

public class XYCompression18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> noDupList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            noDupList.add(entry.getKey());
        }
        noDupList.sort(Comparator.naturalOrder());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            int index = binarySearch(arr[i],noDupList);
            sb.append(index).append(" ");
        }
        System.out.print(sb);
    }

    static int binarySearch(int target, List<Integer> list){
        int start = 0; int end = list.size() - 1;

        while(start <= end){
            int mid = (start + end ) / 2;
            int present = list.get(mid);

            if(present == target) return mid;
            else if(present > target) end = mid - 1;
            else start = mid + 1;
        }
        return -1;
    }
}