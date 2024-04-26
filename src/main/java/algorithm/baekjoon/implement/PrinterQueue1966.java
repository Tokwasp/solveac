package algorithm.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PrinterQueue1966 {
    public static class Priority{
        int num = 0;
        int priority = 0;
        Priority(int num, int priority){
            this.num = num;
            this.priority = priority;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int N = 0, targetIndex= 0, caseNum = 0, cnt = 0;
        caseNum = Integer.parseInt(br.readLine());

        for(int i=0; i<caseNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            targetIndex = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            LinkedList<Priority> q = new LinkedList();
            for(int j=0; j<N; j++) q.offer(new Priority(j,Integer.parseInt(st.nextToken())));
            Priority target = q.get(targetIndex);

            cnt = 0;
            boolean complete = false;
            while(!q.isEmpty()){
                Priority poll = q.poll();
                complete = true;
                for(int j=0; j<q.size(); j++) {
                    if(poll.priority < q.get(j).priority){
                        q.offer(poll);
                        complete = false;
                        break;
                    }
                }
                if(complete) cnt++;
                if(complete && poll.num == target.num) break;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
