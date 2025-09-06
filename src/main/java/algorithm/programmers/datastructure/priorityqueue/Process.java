package algorithm.programmers.datastructure.priorityqueue;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Process {
    public int solution(int[] priorities, int targetIndex) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        Queue<Job> queue = new ArrayDeque<>();

        // 작업 넣기
        for (int i = 0; i < priorities.length; i++) {
            Job job = new Job(priorities[i], i);
            pq.add(priorities[i]);
            queue.add(job);
        }

        // 작업 수행
        while (!queue.isEmpty()) {
            Job job = queue.poll();
            int priority = job.priority;

            // 최우선순위 작업이라면
            if (pq.peek() == priority) {
                pq.poll();

                // 작업의 인덱스가 찾고자 하는 인덱스 라면
                if (job.index == targetIndex) {
                    return priorities.length - pq.size();
                }
            } else {
                // 작업 수행 불가
                queue.add(job);
            }
        }

        // 찾지 못하는 경우 (없음)
        return -1;
    }

    private static class Job {
        private int priority;
        private int index;

        public Job(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }
    }
}
