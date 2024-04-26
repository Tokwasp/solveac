package algorithm.baekjoon.greedy;

import java.util.Scanner;

public class GameDongjun2847 {
    static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] level = new int[N];

        // 레벨 점수 받기
        for(int i=0; i<N; i++) level[i] = sc.nextInt();

        //N이 1개면 검사 의미가 없으므로 return;
        if(N == 1){
            System.out.println("0");
            return;
        }
        System.out.println(levelCheck(level,0));
    }
    static int levelCheck(int[] level, int start){
        for(int i=start; i < level.length-1; i++){
            if(level[i] >= level[i+1]) {
                level[i]--;
                count++;
            }
            if(i !=0 && level[i-1] >= level[i]) levelCheck(level, i - 1);
        }
        return count;
    }
}
