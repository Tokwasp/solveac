package algorithm.baekjoon.simulation;

import java.io.*;
import java.util.StringTokenizer;

public class Baseball17281 {
    static int[][] arr;
    static int N;
    static int maxPoint = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][10];

        // 입력 받기
        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1 ~ 9 번 선수
        boolean[] visited = new boolean[10];
        // order index = 차례 order[i] = 몇번 타자
        int[] order = new int[10];

        // 4번 타자는 1번째
        order[4] = 1; visited[4] = true;
        permutation(2,order,visited);

        System.out.println(maxPoint);
    }

    // 순열 찾기
    static void permutation(int count, int[] order, boolean[] visited){
        // 9명의 선수를 찾은 재귀는 10을 호출
        if(count == 10){
            baseballPlay(order);
            return;
        }

        // visited[i] = i번째 선수 뽑았 는지, count = 몇 번째, order[i] = count -> i번째 선수는 count 타자 입니다.
        for(int i = 1; i <= 9; i++){
            if(!visited[i]) {
                visited[i] = true;
                order[i] = count;
                permutation(count + 1, order, visited);
                visited[i] = false;
            }
        }
    }

    static void baseballPlay(int[] order){
        int Point = 0;
        int batter = 0;

        for(int i = 1; i <= N; i++) {
            int out = 0;
            boolean[] roo = new boolean[4];
            int[] batterResult = makeArr(order,i);

            // 3아웃이 아니면
            while (out != 3) {
                int result = batterResult[batter % 9]; batter++;
                if(result == 0) out++;
                else Point += goRoo(roo,result);
            }
        }
        maxPoint = Math.max(Point,maxPoint);
    }

    // 타자의 결과에 따라 점수로 바꿔 줍니다.
    static int goRoo(boolean[] roo, int result){
        int Point = 0;

        for(int i = 3; i > 0; i--){
            if(roo[i]){
                if(i + result >= 4) {
                    Point++;
                    roo[i] = false;
                }
                else{
                    roo[i] = false;
                    roo[result + i] = true;
                }
            }
        }
        if(result != 4) roo[result] = true;
        else Point++;

        return Point;
    }

    // order[i] : 순번, arr[inning][order[i]] = 이닝의 타자의 결과
    static int[] makeArr(int[] order, int inning){
        int[] result = new int[9];

        for(int i = 1; i <= 9; i++){
            result[i-1] = arr[inning][order[i]];
        }
        return result;
    }
}