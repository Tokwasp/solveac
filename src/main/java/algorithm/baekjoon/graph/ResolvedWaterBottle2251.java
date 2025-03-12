package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ResolvedWaterBottle2251 {

    public static final int MAX_BOTTLE_SIZE = 201;
    public static final int BOTTLE_TOTAL_SIZE = 3;
    static int[] maxWaterBottles;
    static boolean[][][] visited;
    static List<Integer> CBottleRemainWaters;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] waterBottleInput = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int aBottleSize = waterBottleInput[0];
        int bBottleSize = waterBottleInput[1];
        int cBottleSize = waterBottleInput[2];

        maxWaterBottles = new int[]{aBottleSize, bBottleSize, cBottleSize};
        visited = new boolean[MAX_BOTTLE_SIZE][MAX_BOTTLE_SIZE][MAX_BOTTLE_SIZE];
        CBottleRemainWaters = new ArrayList<>();

        bfs(0, 0, cBottleSize);

        CBottleRemainWaters.sort(Comparator.naturalOrder());
        StringBuilder sb = new StringBuilder();
        fillStringBuilderFromCBottleWater(sb);
        System.out.print(sb);
    }

    public static void bfs(int a, int b, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a, b, c});
        visited[a][b][c] = true;
        CBottleRemainWaters.add(c);

        while (!queue.isEmpty()) {
            int[] bottles = queue.poll();

            for (int standardIndex = 0; standardIndex < BOTTLE_TOTAL_SIZE; standardIndex++) {
                for (int targetIndex = 0; targetIndex < BOTTLE_TOTAL_SIZE; targetIndex++) {

                    if (targetIndex == standardIndex) continue;
                    int[] nextBottles = copyBottle(bottles);

                    int moveWaterAmount = calculateMoveWaterAmount(nextBottles, standardIndex, targetIndex);
                    moveWater(nextBottles, moveWaterAmount, targetIndex, standardIndex);

                    if (!visited[nextBottles[0]][nextBottles[1]][nextBottles[2]]) {
                        visited[nextBottles[0]][nextBottles[1]][nextBottles[2]] = true;
                        queue.add(nextBottles);

                        if (isZeroRemainWaterFromABottle(nextBottles)) {
                            CBottleRemainWaters.add(nextBottles[2]);
                        }
                    }
                }
            }
        }
    }

    private static int[] copyBottle(int[] bottles) {
        int[] nextBottle = new int[3];
        System.arraycopy(bottles, 0, nextBottle, 0, BOTTLE_TOTAL_SIZE);
        return nextBottle;
    }

    private static int calculateMoveWaterAmount(int[] bottles, int standardIndex, int targetIndex) {
        int maxReceiveWaterToTarget = maxWaterBottles[targetIndex] - bottles[targetIndex];
        int maxMoveWaterFromStandard = bottles[standardIndex];
        return Math.min(maxReceiveWaterToTarget, maxMoveWaterFromStandard);
    }

    private static void moveWater(int[] nextBottles, int moveWaterAmount, int targetIndex, int standardIndex) {
        nextBottles[standardIndex] -= moveWaterAmount;
        nextBottles[targetIndex] += moveWaterAmount;
    }

    private static boolean isZeroRemainWaterFromABottle(int[] nextBottles) {
        return nextBottles[0] == 0;
    }

    private static void fillStringBuilderFromCBottleWater(StringBuilder sb) {
        for (int num : CBottleRemainWaters) {
            sb.append(num).append(" ");
        }
    }
}