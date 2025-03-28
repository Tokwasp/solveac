package algorithm.baekjoon.graph;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class ConveyorBelt20055 {
    static int[] durability;
    static boolean[] isOnConveyorBelt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 입력 받기
        int halfBeltSize = Integer.parseInt(st.nextToken());
        int beltSize = halfBeltSize * 2;
        int targetZeroDurabilityCount = Integer.parseInt(st.nextToken());

        isOnConveyorBelt = new boolean[beltSize];
        durability = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int step = 0;
        while (true) {
            step++;
            rotateBelt();
            moveRobots();
            addRobot();

            if (checkZeroDurability() >= targetZeroDurabilityCount) {
                break;
            }
        }
        System.out.println(step);
    }

    static void rotateBelt() {
        int lastDurability = durability[durability.length - 1];
        boolean lastRobotIsOnConveyorBelt = isOnConveyorBelt[isOnConveyorBelt.length - 1];

        for (int i = durability.length - 1; i > 0; i--) {
            durability[i] = durability[i - 1];
            isOnConveyorBelt[i] = isOnConveyorBelt[i - 1];
        }

        durability[0] = lastDurability;
        isOnConveyorBelt[0] = lastRobotIsOnConveyorBelt;

        isOnConveyorBelt[durability.length / 2 - 1] = false;
    }

    static void moveRobots() {
        int lastIndex = isOnConveyorBelt.length - 1;
        boolean lastRobotIsOnConveyorBelt = isOnConveyorBelt[lastIndex];

        for (int index = lastIndex; index > 0; index--) {
            if (!isOnConveyorBelt[index] && durability[index] >= 1 && isOnConveyorBelt[index -1]) {
                isOnConveyorBelt[index] = isOnConveyorBelt[index -1];
                isOnConveyorBelt[index - 1] = false;
                durability[index]--;
            }
        }

        if(!isOnConveyorBelt[0] && durability[0] >= 1 && isOnConveyorBelt[lastIndex]) {
            isOnConveyorBelt[0] = lastRobotIsOnConveyorBelt;
            durability[0]--;
        }

        isOnConveyorBelt[isOnConveyorBelt.length / 2 - 1] = false;
    }

    static void addRobot() {
        if (durability[0] > 0 && !isOnConveyorBelt[0]) {
            isOnConveyorBelt[0] = true;
            durability[0]--;
        }
    }

    static int checkZeroDurability() {
        int count = 0;
        for (int d : durability) {
            if (d == 0) count++;
        }
        return count;
    }
}
