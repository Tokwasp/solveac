package algorithm.baekjoon.greedy;

import java.io.*;
import java.util.PriorityQueue;

public class CardSort1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());

        //카드에 자료값 넣기
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(br.readLine());
            priorityQueue.offer(num);
        }

        int sum = 0;
        //우선순위 큐에서 가장 작은 묶음 두개를 꺼내서 더한후 큐에 넣고 반복 한다.
        while(!priorityQueue.isEmpty()){
            //큐에 남는게 하나 일경우 종료 한다.
            if(priorityQueue.size() == 1) break;
            else {
                int num1 = priorityQueue.poll();
                int num2 = priorityQueue.poll();
                sum += num1 + num2;
                priorityQueue.offer(num1 + num2);
            }
        }
        if(n == 1) System.out.print(0);
        else System.out.print(sum);
    }
}
