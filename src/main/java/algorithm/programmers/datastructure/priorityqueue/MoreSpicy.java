package algorithm.programmers.datastructure.priorityqueue;

import java.util.PriorityQueue;

public class MoreSpicy {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < scoville.length; i++) {
            pq.add(Long.valueOf(scoville[i]));
        }

        int count = 0;
        while (!pq.isEmpty()) {
            if (pq.peek() < K) {
                long spicy = pq.poll();

                if (pq.isEmpty()) {
                    return -1;
                }

                long spicy2 = pq.poll();
                pq.add(spicy + spicy2 * 2);
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
