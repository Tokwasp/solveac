package algorithm.baekjoon.graph;

import java.io.*;
import java.util.*;

public class SangBumBuilding6593 {
    static int C = 0, R = 0, L = 0;
    static int[][][] minuteMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            if(C == 0 && R == 0 && L == 0){
                break;
            }

            String[][][] map = new String[C][R][L];
            minuteMap = new int[C][R][L];
            Node start = null;

            for(int floor = 0; floor < C; floor++){
                for(int row = 0; row < R; row++){
                    String[] input = br.readLine().split("");
                    for(int col = 0; col < L; col++){
                        map[floor][row][col] = input[col];
                        if(map[floor][row][col].equals("S")){
                            start = new Node(floor, row, col);
                        }
                    }
                }
                br.readLine();
            }

            int escapeMinute = bfs(map, start);
            if(escapeMinute != 0){
                sb.append("Escaped in ").append(escapeMinute).append(" ").append("minute(s).").append("\n");
            }
            else{
                sb.append("Trapped!").append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int bfs(String[][][] map, Node start){
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,1,-1};
        int[] dz = {-1,1};

        Queue<Node> q = new ArrayDeque<>();
        q.add(start);
        boolean[][][] visited = new boolean[C][R][L];
        visited[start.floor][start.row][start.col] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(map[cur.floor][cur.row][cur.col].equals("E")){
                return minuteMap[cur.floor][cur.row][cur.col];
            }

            for(int i = 0; i < 4; i++) {
                int nextFloor = cur.floor;
                int nextRow = cur.row + dx[i];
                int nextCol = cur.col + dy[i];

                if(isMapIn(nextFloor,nextRow,nextCol) && !visited[nextFloor][nextRow][nextCol] && !map[nextFloor][nextRow][nextCol].equals("#")){
                    visited[nextFloor][nextRow][nextCol] = true;
                    minuteMap[nextFloor][nextRow][nextCol] = minuteMap[cur.floor][cur.row][cur.col] + 1;
                    q.add(new Node(nextFloor, nextRow, nextCol));
                }
            }

            for(int i = 0; i < 2; i++){
                int nextFloor = cur.floor + dz[i];
                int nextRow = cur.row;
                int nextCol = cur.col;

                if(isMapIn(nextFloor,nextRow,nextCol) && !visited[nextFloor][nextRow][nextCol] && !map[nextFloor][nextRow][nextCol].equals("#")){
                    visited[nextFloor][nextRow][nextCol] = true;
                    minuteMap[nextFloor][nextRow][nextCol] = minuteMap[cur.floor][cur.row][cur.col] + 1;
                    q.add(new Node(nextFloor, nextRow, nextCol));
                }
            }
        }
        return 0;
    }

    private static boolean isMapIn(int nextFloor, int nextRow, int nextCol) {
        return 0 <= nextFloor && nextFloor <= C - 1
                && 0 <= nextRow && nextRow <= R -1
                && 0 <= nextCol && nextCol <= L -1;
    }

    static class Node{
        int floor;
        int row;
        int col;

        public Node(int floor, int row, int col) {
            this.floor = floor;
            this.row = row;
            this.col = col;
        }
    }
}