package algorithm.programmers.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

public class TruckOnTheBridge {
    public int solution(int length, int maxWeight, int[] trucks) {
        Queue<Integer> waitQ = new LinkedList<>();
        Queue<Truck> moveQ = new LinkedList<>();

        for (int i = 0; i < trucks.length; i++) {
            waitQ.add(trucks[i]);
        }

        int curWeight = 0;
        int time = 0;
        while (!waitQ.isEmpty() || !moveQ.isEmpty()) {
            time++;

            int size = moveQ.size();
            for (int i = 0; i < size; i++) {
                Truck truck = moveQ.poll();
                truck.move();

                if (truck.move > length) {
                    curWeight -= truck.weight;
                } else {
                    moveQ.add(truck);
                }
            }

            if (!waitQ.isEmpty() && curWeight + waitQ.peek() <= maxWeight) {
                Truck truck = new Truck(waitQ.poll());
                moveQ.add(truck);
                curWeight += truck.weight;
            }
        }
        return time;
    }

    static class Truck {
        private int weight;
        private int move;

        public Truck(int weight) {
            this.weight = weight;
            this.move = 1;
        }

        public String toString() {
            return String.valueOf(weight + " " + move);
        }

        public void move() {
            this.move += 1;
        }
    }
}
