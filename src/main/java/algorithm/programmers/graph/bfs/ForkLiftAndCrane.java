package algorithm.programmers.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ForkLiftAndCrane {
    private static final char BLANK_BLOCK = '0';

    private static char[][] blocks;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public int solution(String[] storages, String[] requests) {
        blocks = new char[storages.length + 2][storages[0].length() + 2];

        // map 초기화
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[0].length; col++) {
                blocks[row][col] = BLANK_BLOCK;
            }
        }

        for (int row = 1; row <= storages.length; row++) {
            String storage = storages[row - 1];

            for (int col = 1; col <= storage.length(); col++) {
                char ch = storage.charAt(col - 1);
                blocks[row][col] = ch;
            }
        }

        int removeBlockCount = 0;
        for (int i = 0; i < requests.length; i++) {
            String request = requests[i];
            int command = request.length();
            char removeBlock = request.charAt(0);

            if (command == 1) {
                removeBlockCount += outsideRemoveBlock(removeBlock);
            }

            if (command == 2) {
                removeBlockCount += anyMatchBlockRemoveBlock(removeBlock);
            }
        }

        int totalBlockCount = storages.length * storages[0].length();
        return totalBlockCount - removeBlockCount;
    }

    private static int outsideRemoveBlock(char removeBlock) {
        boolean[][] visited = new boolean[blocks.length][blocks[0].length];
        Queue<int[]> sideCheckQueue = new LinkedList<>();
        Queue<int[]> removeBlockQueue = new LinkedList<>();

        sideCheckQueue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!sideCheckQueue.isEmpty()) {
            int[] dots = sideCheckQueue.poll();
            int row = dots[0];
            int col = dots[1];

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];

                if (!isMapIn(nextRow, nextCol) || visited[nextRow][nextCol]) continue;

                if (blocks[nextRow][nextCol] == BLANK_BLOCK) {
                    sideCheckQueue.add(new int[]{nextRow, nextCol});
                }

                if (blocks[nextRow][nextCol] == removeBlock) {
                    removeBlockQueue.add(new int[]{nextRow, nextCol});
                }

                visited[nextRow][nextCol] = true;
            }
        }

        int removeBlockCount = removeBlockQueue.size();
        while (!removeBlockQueue.isEmpty()) {
            int[] blockDot = removeBlockQueue.poll();
            int row = blockDot[0];
            int col = blockDot[1];

            blocks[row][col] = BLANK_BLOCK;
        }
        return removeBlockCount;
    }

    private static int anyMatchBlockRemoveBlock(char removeBlock) {
        Queue<int[]> removeBlockQueue = new LinkedList<>();

        for (int row = 1; row < blocks.length - 1; row++) {
            for (int col = 1; col < blocks[0].length - 1; col++) {
                char block = blocks[row][col];
                if (block == BLANK_BLOCK || block != removeBlock) continue;

                removeBlockQueue.add(new int[]{row, col});
            }
        }

        int removeBlockCount = removeBlockQueue.size();
        while (!removeBlockQueue.isEmpty()) {
            int[] blockDot = removeBlockQueue.poll();
            int row = blockDot[0];
            int col = blockDot[1];

            blocks[row][col] = BLANK_BLOCK;
        }
        return removeBlockCount;
    }

    private static boolean isMapIn(int row, int col) {
        return 0 <= row && row <= blocks.length - 1 &&
                0 <= col && col <= blocks[0].length - 1;
    }
}
