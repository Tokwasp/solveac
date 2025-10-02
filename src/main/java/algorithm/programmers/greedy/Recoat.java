package algorithm.programmers.greedy;

import java.util.LinkedList;
import java.util.Queue;

public class Recoat {
    public int solution(int wallLength, int rollerLength, int[] section) {
        Queue<Integer> queue = new LinkedList<>();
        for (int num : section) {
            queue.add(num);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int start = queue.poll();
            int end = start + rollerLength - 1;

            while (!queue.isEmpty() && queue.peek() <= end) {
                queue.poll();
            }
            count++;
        }
        return count;
    }
}
