package algorithm.programmers.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfServerExpansion {
    public int solution(int[] playerCounts, int serverCapacity, int serverActionHour) {
        int serverCount = 0;
        Queue<Integer> serverActionTimeQueue = new LinkedList();

        for(int time = 0; time < playerCounts.length; time++){
            while(!serverActionTimeQueue.isEmpty() && serverActionTimeQueue.peek() == time){
                serverActionTimeQueue.poll();
            }

            int people = playerCounts[time];
            int peopleCapacity = (serverActionTimeQueue.size() + 1) * serverCapacity - 1;

            while(peopleCapacity < people){
                serverActionTimeQueue.add(time + serverActionHour);
                peopleCapacity = (serverActionTimeQueue.size() + 1) * serverCapacity - 1;
                serverCount++;
            }
        }
        return serverCount;
    }
}
