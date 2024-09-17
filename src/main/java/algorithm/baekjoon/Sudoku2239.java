package algorithm.baekjoon;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Sudoku2239 {
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static boolean pass = false;
    static List<int[]> blankList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        blankList = new ArrayList<>();

        for(int i = 0; i < map.length; i++){
            map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == 0) blankList.add(new int[] {i,j});
            }
        }

        dfs(0);
        System.out.print(sb);
    }

    static void dfs(int idx){
        if(idx == blankList.size()){
            printMap();
            pass = true;
            return;
        }

        int[] rowAndCol = blankList.get(idx);
        int row = rowAndCol[0]; int col = rowAndCol[1];

        for(int i = 1; i <= 9; i++){
            map[row][col] = i;
            if(checkSector(row,col) && checkRowAndCol(row, col)){
                dfs(idx + 1);
            }
            if(pass) return;
            map[row][col] = 0;
        }
    }

    static boolean checkSector(int row, int col){
        int rowSector = row / 3 * 3;
        int colSector = col / 3 * 3;

        for(int i = rowSector; i < rowSector + 3; i++){
            for(int j = colSector; j < colSector + 3; j++){
                //자기 자신 제외
                if(!(row == i && col == j)){
                    //다른 부분이 자기 자신과 같다면
                    if(map[i][j] == map[row][col]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean checkRowAndCol(int row, int col){
        // 열 검사
        for(int i = 0; i < map[0].length; i++){
            //자기 자신 제외
            if(i != col && map[row][i] == map[row][col]){
                return false;
            }
        }

        // 행 검사
        for(int i = 0; i < map.length; i++){
            //자기 자신 제외
            if(i != row && map[i][col] == map[row][col]){
                return false;
            }
        }
        return true;
    }

    static void printMap(){
        for (int[] row : map) {
            for (int num : row) {
               sb.append(num);
            }
            sb.append("\n");
        }
    }
}
