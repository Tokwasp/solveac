package algorithm.baekjoon.greedy;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class TwoArraySum2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int[] arrA = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        int[] arrB = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        // arrA의 부배열
        for(int i = 0; i < N; i++){
            int count = 0;
            for(int j = i; j < N; j++){
                count += arrA[j];
                listA.add(count);
            }
        }

        // arrB의 부배열
        for(int i = 0; i < M; i++){
            int count = 0;
            for(int j = i; j < M; j++){
                count += arrB[j];
                listB.add(count);
            }
        }

        // listA 오름차순 정렬
        Collections.sort(listA);
        // listB 내림차순 정렬
        listB.sort(Comparator.reverseOrder());

        int aPointer = 0;
        int bPointer = 0;
        long count = 0;

        while (aPointer < listA.size() && bPointer < listB.size()) {
            int sum = listA.get(aPointer) + listB.get(bPointer);

            // pointer index 합이 T와 같을 경우
            if (sum == T) {
                long aCount = 1, bCount = 1;

                // listA 동일한 값을 가진 요소 개수
                while (aPointer + 1 < listA.size() && listA.get(aPointer).equals(listA.get(aPointer + 1))) {
                    aPointer++;
                    aCount++;
                }

                // listB 동일한 값을 가진 요소 개수
                while (bPointer + 1 < listB.size() && listB.get(bPointer).equals(listB.get(bPointer + 1))) {
                    bPointer++;
                    bCount++;
                }

                count += aCount * bCount;
                aPointer++; bPointer++;

            } else if (sum < T) aPointer++;
             else bPointer++;
        }
        System.out.println(count);
    }
}