package algorithm.swea.d3;

import java.io.*;
import java.util.stream.Stream;

public class Diet {
    static int foodNum;
    static int totalCalorie;
    static int[][] scoreAndCalorie;
    static int max;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for (int j = 1; j <= testCase; j++) {
            int[] foodNumAndCalorie = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            foodNum = foodNumAndCalorie[0];
            totalCalorie = foodNumAndCalorie[1];

            // 0열 : 점수 , 1열 : 칼로리
            scoreAndCalorie = new int[foodNum][2];

            for (int i = 0; i < foodNum; i++) {
                int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                scoreAndCalorie[i][0] = input[0];
                scoreAndCalorie[i][1] = input[1];
            }

            dfs(0, 0, 0);
            String st = "#" + j + " " + max;
            sb.append(st).append("\n");
            max = 0;
        }
        System.out.print(sb);
    }

    static void dfs(int index, int sumScore, int sumCalorie){

        if(sumCalorie > totalCalorie) return;

        if(index == foodNum){
            max = Math.max(max,sumScore);
            return;
        }

        int score = scoreAndCalorie[index][0];
        int calorie = scoreAndCalorie[index][1];

        //선택 하는 경우
        dfs(index + 1, sumScore + score, sumCalorie + calorie);
        //선택 하지 않는 경우
        dfs(index + 1, sumScore, sumCalorie);
    }
}
