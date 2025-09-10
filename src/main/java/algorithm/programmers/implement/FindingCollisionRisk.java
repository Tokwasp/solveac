package algorithm.programmers.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindingCollisionRisk {
    private static int[][] map;
    private static Map<Integer, int[]> pointMap;
    private static List<Robot> robots;

    public int solution(int[][] points, int[][] routes) {
        map = new int[101][101];
        pointMap = new HashMap<>();
        robots = new ArrayList<>();

        // 포인트 입력 받기
        for (int row = 0; row < points.length; row++) {
            int pointNumber = row + 1;
            pointMap.put(pointNumber, new int[]{points[row][0], points[row][1]});
        }

        // 로봇 입력 받기
        for (int row = 0; row < routes.length; row++) {
            int robotNumber = row + 1;

            int currentPoint = routes[row][0];
            int[] coordinates = pointMap.get(currentPoint);

            Robot robot = new Robot(robotNumber, coordinates[0], coordinates[1]);
            robots.add(robot);
            map[robot.row][robot.col] += 1;

            for (int col = 1; col < routes[0].length; col++) {
                int nextPoint = routes[row][col];
                robot.points.add(nextPoint);
            }
        }

        // 출발전 겹침 체크
        int count = 0;
        count += findCountToDuplicate();

        // 움직이기
        while (!robots.isEmpty()) {
            for (int i = 0; i < robots.size(); i++) {
                Robot robot = robots.get(i);

                int nextPoint = robot.points.get(0);
                int[] nextDestination = pointMap.get(nextPoint);

                int[] moves = robot.findDirection(nextDestination);
                map[robot.row][robot.col] -= 1;
                robot.move(moves);
                map[robot.row][robot.col] += 1;
            }

            // 겹친것 체크
            count += findCountToDuplicate();

            // 목적지 도착 제거
            for (int i = robots.size() - 1; i >= 0; i--) {
                Robot robot = robots.get(i);

                int nextPoint = robot.points.get(0);
                int[] nextDestination = pointMap.get(nextPoint);

                if (robot.isDestination(nextDestination)) {
                    robot.points.remove(0);
                }

                if (robot.points.isEmpty()) {
                    map[robot.row][robot.col] -= 1;
                    robots.remove(i);
                }
            }
        }
        return count;
    }

    private static int findCountToDuplicate() {
        int count = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] >= 2) {
                    count++;
                }
            }
        }
        return count;
    }

    private static class Robot {
        public int num;
        public int row;
        public int col;
        public List<Integer> points;

        public Robot(int num, int row, int col) {
            this.num = num;
            this.row = row;
            this.col = col;
            this.points = new ArrayList<>();
        }

        public int[] findDirection(int[] nextPoints) {
            int targetRow = nextPoints[0];
            int targetCol = nextPoints[1];

            if (row - targetRow < 0) return new int[]{1, 0};
            if (row - targetRow > 0) return new int[]{-1, 0};

            if (col - targetCol < 0) return new int[]{0, 1};
            if (col - targetCol > 0) return new int[]{0, -1};

            return new int[]{0, 0};
        }

        public void move(int[] directions) {
            row += directions[0];
            col += directions[1];
        }

        public boolean isDestination(int[] destinations) {
            return row == destinations[0] && col == destinations[1];
        }
    }
}
