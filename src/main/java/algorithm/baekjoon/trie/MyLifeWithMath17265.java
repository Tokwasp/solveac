package algorithm.baekjoon.trie;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class MyLifeWithMath17265 {
    static char[][] map;
    static Set<Character> numSet;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        // map 데이터 넣기
        for(int i = 0; i < map.length; i++){
            String[] s = br.readLine().split(" ");
            for(int j = 0; j < map[0].length; j++){
                map[i][j] = s[j].charAt(0);
            }
        }

        numSet = new HashSet<>();
        // number 값 set에 넣기
        for(int i = 0; i <= 5; i++){
            String st = String.valueOf(i);
            numSet.add(st.charAt(0));
        }

        dfs(0,0,'+',0);

        System.out.println(max + " " + min);
    }

    static void dfs(int row, int col, char operator, int total) {
        char present = map[row][col];
        char newUnsan = operator;
        int newTotal = total;

        if(numSet.contains(present)){
            int num = present - '0';

            if(newUnsan == '+') newTotal += num;
            else if(newUnsan == '-') newTotal -= num;
            else if(newUnsan == '*') newTotal *= num;
        }
        else{
            newUnsan = present;
        }

        if(row == map.length - 1 && col == map.length - 1){
            min = Math.min(min,newTotal);
            max = Math.max(max,newTotal);
        }

        int[] dx = {0,1};
        int[] dy = {1,0};

        for(int i = 0; i < 2; i++){
            int nextX = row + dx[i];
            int nextY = col + dy[i];

            boolean error =  nextX > (map.length - 1) || nextY > (map.length - 1);

            if(!error){
                dfs(nextX, nextY, newUnsan, newTotal);
            }
        }
    }
}
