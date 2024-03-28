package implement;

import java.util.*;

/*
    풀이 : 문제가 맨위에서 뽑고 그후에 제일 아래로 넣는다 그림을 그려보면 큐를 뒤집어놓은 모양이다.
 */
public class Card2164 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();

        for(int i=1; i<=n; i++){
            q.add(i);
        }

        while(q.size() != 1){
            q.poll();
            if(q.size() !=1) {
               q.add(q.poll());
            }
        }
        System.out.print(q.poll());
    }
}
