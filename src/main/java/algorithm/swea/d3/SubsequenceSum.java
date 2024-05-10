package algorithm.swea.d3;

import java.util.Scanner;

/*
  문제 풀이 : dfs (백 트래킹)

  이진 트리 문제로 배열의 숫자를 포함 하는지 안 하는지 두 가지 경우로 DFS 실행 한다.

  dfs(int index, int total) :
  index : 배열의 arr[index]에 접근 , 종료 조건 체크
  total : 현재 까지의 합

  재귀 호출 조건 :
  1. 배열의 숫자를 포함 하는 경우 , 2. 배열의 숫자를 포함 하지 않는 경우

  count 증가 조건 :
  total(현재 까지의 합) == K ( 찾는 수 )

  종료 조건 :
  1. dfs(index,total) 배열을 모두 탐색 한 경우 ex) 배열의 길이 5 , index : 5
  2. total >= K ( >= 인 이유는 먼저 total == k를 확인 하기 때문)

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
