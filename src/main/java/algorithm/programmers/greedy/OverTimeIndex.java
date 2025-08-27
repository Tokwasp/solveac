package algorithm.programmers.greedy;

import java.util.PriorityQueue;

public class OverTimeIndex {
    public long solution(int n, int[] works) {
        long answer = 0;
        int sum = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
            sum += works[i];
        }

        if (sum < n) return 0;

        while (n != 0) {
            pq.add(pq.poll() - 1);
            n--;
        }

        while (!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}
