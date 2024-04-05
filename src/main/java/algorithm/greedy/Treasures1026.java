package algorithm.greedy;

import java.util.*;
/*
 B배열을 정렬하지 않고 어떻게 순위를 구하는지에 대해서 알지 못했다.
 다른분들의 풀이를 보니 우선순위 큐를 사용한다고 하였다. 하지만 우선순위큐를 알지못했다.. 기본을 더 공부하자

 */
public class Treasures1026 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int[] A = new int[number];
        int sum = 0;

        //Comparator 내림차순 람다식으로 구현해보기
        PriorityQueue<Integer> B = new PriorityQueue<>((a,b) -> {
            int x = Integer.parseInt(a.toString());
            int y = Integer.parseInt(b.toString());

            if(x > y) return -1;
            else if (x == y) return 0;
            else return 1;
        });

        for(int i=0; i<number*2; i++){
            if(i<number) A[i] = sc.nextInt();
            else B.add(sc.nextInt());
        }

        Arrays.sort(A);

        for(int i=0; i<number; i++){
            if(!B.isEmpty()) {
                sum += B.poll() * A[i];
            }
        }
        System.out.println(sum);
    }
}
