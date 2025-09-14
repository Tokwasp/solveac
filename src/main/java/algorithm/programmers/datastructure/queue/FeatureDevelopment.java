package algorithm.programmers.datastructure.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FeatureDevelopment {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<int[]> queue = new LinkedList<>();

        // 입력
        for (int i = 0; i < progresses.length; i++) {
            int progress = progresses[i];
            queue.add(new int[]{progress, i});
        }

        List<Integer> list = new ArrayList<>();

        // 작업 실행
        int day = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int progress = poll[0];
            int index = poll[1];
            int speed = speeds[index];

            int curProgress = progress + speed * day;

            // 작업 안 끝난 경우
            while (curProgress < 100) {
                curProgress += speed;
                day++;
            }
            count++;

            // 큐에서 작업 끝난것 있는지 체크
            while (!queue.isEmpty() && queue.peek()[0] + speeds[queue.peek()[1]] * day >= 100) {
                queue.poll();
                count++;
            }

            list.add(count);
            count = 0;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
