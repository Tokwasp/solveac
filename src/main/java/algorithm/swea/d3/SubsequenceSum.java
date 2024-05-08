package algorithm.swea.d3;

import java.util.Scanner;

/*
  문제 풀이 : dfs (백 트래킹)

  실행 조건 :
  이진 트리 문제로 배열의 숫자를 포함 하는지 안 하는지 두 가지 경우로 DFS 실행 한다.
  종료 조건 :
  dfs(index,total)의 index가 배열의 크기와 같아 지거나, K <= total 일 경우 종료 한다.

 */
public class SubsequenceSum {
    static int count = 0;
    static int N;
    static int K;
    static int[] arr;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int testCase = sc.nextInt();

        for(int t = 1; t <= testCase; t++) {
            N = sc.nextInt();
            K = sc.nextInt();

            arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            dfs(0, 0);
            String st = "#" + t + " " + count;
            sb.append(st).append("\n");
            count = 0;
        }
        System.out.print(sb);
    }

    static void dfs(int index, int total){

        if(total == K) count++;

        //배열의 마지막 인덱스 까지 접근 하거나, 구하는 수보다 total 이상 일 경우  return
        if(index == N || total >= K) return;

        int num = arr[index];
        int newTotal = num + total;

        //숫자를 선택 하는 경우
        dfs(index + 1, newTotal);
        //숫자를 선택 하지 않는 경우
        dfs(index + 1 , total);
    }
}
