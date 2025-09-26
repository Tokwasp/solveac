package algorithm.programmers.datastructure.priorityqueue;

import java.util.PriorityQueue;

public class DefenseGame {
    public int solution(int life, int shildCount, int[] enemies) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < enemies.length; i++) {
            int enemy = enemies[i];

            life -= enemy;
            pq.add(enemy);

            if (life < 0) {
                if (shildCount == 0) return i;

                shildCount--;
                life += pq.poll();
            }
        }
        return enemies.length;
    }

    // 재풀이
    public int solution2(int life, int shildCount, int[] enemies) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);

        for(int i = 0 ; i < enemies.length; i++){
            int enemy = enemies[i];
            life -= enemy;
            pq.add(enemy);

            if(life < 0 && shildCount > 0){
                life += pq.poll();
                shildCount--;
            }

            if(life < 0){
                return i;
            }
        }
        return enemies.length;
    }
}
