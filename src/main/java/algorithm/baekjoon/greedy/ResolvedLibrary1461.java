package algorithm.baekjoon.greedy;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ResolvedLibrary1461{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int bookCount = input[0];
        int moveableMaxBookCount = input[1];

        PriorityQueue<Integer> plusDistPriorityQueue = new PriorityQueue<>((a,b) -> b - a);
        PriorityQueue<Integer> minusDistPriorityQueue = new PriorityQueue<>((a,b) -> b -a);

        //입력 받기
        int[] bookDistInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int index = 0; index < bookCount; index++){
            int bookDist = bookDistInput[index];
            if(bookDist < 0){
                minusDistPriorityQueue.add(bookDist * -1);
            }
            else{
                plusDistPriorityQueue.add(bookDist);
            }
        }

        // 최대 이동 거리 찾기
        int notReturnToStartDist = 0;
        notReturnToStartDist = findMaxDist(plusDistPriorityQueue);
        notReturnToStartDist = Math.max(findMaxDist(minusDistPriorityQueue), notReturnToStartDist);

        // 이동
        int totalDist = 0;
        totalDist += calculateBackAndForthDist(plusDistPriorityQueue, moveableMaxBookCount);
        totalDist += calculateBackAndForthDist(minusDistPriorityQueue, moveableMaxBookCount);
        System.out.print(totalDist - notReturnToStartDist);
    }

    private static int findMaxDist(PriorityQueue<Integer> priorityQueue) {
        int maxDist = 0;
        if(!priorityQueue.isEmpty()){
            maxDist = priorityQueue.peek();
        }
        return maxDist;
    }

    private static int calculateBackAndForthDist(PriorityQueue<Integer> plusDistPriorityQueue, int moveableMaxBookCount) {
        int totalDist = 0;
        while(!plusDistPriorityQueue.isEmpty()){
            int dist = plusDistPriorityQueue.poll();
            for(int moveIndex = 0; moveIndex < moveableMaxBookCount - 1; moveIndex++){
                plusDistPriorityQueue.poll();
            }
            totalDist += dist * 2;
        }
        return totalDist;
    }
}