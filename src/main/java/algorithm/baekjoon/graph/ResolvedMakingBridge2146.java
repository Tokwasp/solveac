package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ResolvedMakingBridge2146 {
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mapSize = Integer.parseInt(br.readLine());

        map = new int[mapSize][];

        for(int row = 0; row < map.length; row++){
            map[row] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int lastNumber = islandNumbering();
        int min = Integer.MAX_VALUE;
        
        for(int number = 1; number <= lastNumber; number++){
            Queue<Coordinate> queue = findEqualsNumberFromMap(number);
            int distance = findMinDistanceToAnotherIsland(queue, number);
            min = Math.min(distance, min);
        }
        System.out.print(min);
    }

    private static int islandNumbering(){
        boolean[][] visited = new boolean[map.length][map[0].length];
        int number = 1;

        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[0].length; col++){
                if(isNotVisitedIsland(visited, row, col)){
                    numbering(row, col, number, visited);
                    number++;
                }
            }
        }
        return number -1;
    }

    private static boolean isNotVisitedIsland(boolean[][] visited, int row, int col) {
        return !visited[row][col] && map[row][col] == 1;
    }

    private static void numbering(int row, int col, int number, boolean[][] visited) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(row, col));
        visited[row][col] = true;
        map[row][col] = number;

        while(!queue.isEmpty()){
            Coordinate coordinate = queue.poll();

            for(int repeat = 0; repeat < dx.length; repeat++){
                int nextRow = coordinate.row + dx[repeat];
                int nextCol = coordinate.col + dy[repeat];

                if(isMapIn(nextRow,nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == 1){
                    visited[nextRow][nextCol] = true;
                    map[nextRow][nextCol] = number;
                    queue.add(new Coordinate(nextRow, nextCol));
                }
            }
        }
    }

    private static Queue<Coordinate> findEqualsNumberFromMap(int number) {
        Queue<Coordinate> queue = new LinkedList<>();
        for(int row = 0; row < map.length; row++){
            for(int col = 0; col < map[0].length; col++){
                if(map[row][col] == number){
                    queue.add(new Coordinate(row,col,0));
                }
            }
        }
        return queue;
    }

    private static int findMinDistanceToAnotherIsland(Queue<Coordinate> queue, int number) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        Coordinate start = queue.peek();
        visited[start.row][start.col] = true;

        while(!queue.isEmpty()){
            Coordinate curCoordinate = queue.poll();

            if(map[curCoordinate.row][curCoordinate.col] != 0 && map[curCoordinate.row][curCoordinate.col] != number){
                return curCoordinate.dist - 1;
            }

            for(int repeat = 0; repeat < dx.length; repeat++){
                int nextRow = curCoordinate.row + dx[repeat];
                int nextCol = curCoordinate.col + dy[repeat];

                if(isMapIn(nextRow,nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] != number){
                    visited[nextRow][nextCol] = true;
                    queue.add(new Coordinate(nextRow, nextCol, curCoordinate.dist + 1));
                }
            }
        }
        return -1;
    }

    private static boolean isMapIn(int nextRow, int nextCol){
        return 0 <= nextRow && nextRow <= map.length -1 && 0 <= nextCol && nextCol <= map[0].length -1;
    }

    static class Coordinate{
        private int row;
        private int col;
        private int dist;

        public Coordinate(int row, int col){
            this.row = row;
            this.col = col;
        }
        
        public Coordinate(int row, int col, int dist){
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}