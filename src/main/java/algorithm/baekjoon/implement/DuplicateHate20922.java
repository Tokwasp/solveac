package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
* 문제 풀이 :
* 1. map, Queue 사용 한다.
* ( map<Integer,Integer> Key : 숫자 Value : 숫자의 개수 )
* ( Queue<Integer> 현재 큐에 들어 간 크기를 통해 max 값을 갱신 한다. )
*
* 동작 예시)
* 7 2
* 3 2 5 5 5 4 4
*
* 4번째 index 까지 큐,맵에 넣으 면서 최대 값을 갱신 한다 최대값 4
* 5번째 5가 들어 올때 3(5의 개수) > 2 이므로
*
* 큐에서 5 숫자가 나올때 까지 q.poll / map.put (뺀 숫자 개수 갱신) 한다.
* 큐에 남은 배열은 5 5 이다. 이후 끝까지 위 행동을 반복 한다.
* */

public class DuplicateHate20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer,Integer> map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();

        //N, K, max 값
        int[] NAndK = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NAndK[0]; int K = NAndK[1]; int max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++){
            int present = Integer.parseInt(st.nextToken());
            int keyCount = map.getOrDefault(present, 0) + 1;
            map.put(present, keyCount); q.add(present);

            if(keyCount <= K) max = Math.max(max, q.size());
            else{
                while(true){
                    int num = q.poll();
                    int mapValue = map.get(num) - 1;
                    map.put(num, mapValue);

                    if(num == present) break;
                }
            }
        }
        System.out.println(max);

        // 투포인터 풀이
       /*
        int[] count = new int[100001];
        int[] list = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxLength = 0; int start = 0; int end = 0;

        while(end < N){
            if(count[list[end]] < K){
                count[list[end]]++; end++;
                maxLength = Math.max(maxLength, end - start);
            }
            else{
                count[list[start]]--; start++;
            }
        }
        System.out.println(maxLength);
    }*/
    }
}
