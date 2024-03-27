package implement;

import java.util.*;


public class Josephus11866 {

    public static void main(String[] args) {

        //입력값 받기
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int cir = 1;
        Queue<Integer> q = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();

        // q 값채우기
        for(int i=0; i<N; i++){
            q.offer(i+1);
        }

        // K번째 순서에만 큐에서 poll 그외에는 다시 맨뒤로 add한다.
        while(!q.isEmpty()){
            if(cir % K == 0){
                answer.add(q.poll());
                cir = 1;
                continue;
            }
            else q.add(q.poll());

            cir++;
        }

        //결과값 출력
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for(int i=0; i<answer.size(); i++){
            if(i == answer.size()-1) sb.append(answer.get(i));
            else sb.append(answer.get(i)).append(", ");
        }
        sb.append(">");
        System.out.print(sb);
    }
}
