package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class PriorityAndNum{
    int num = 0;
    int priority = 0;
    PriorityAndNum(int num, int priority){
        this.num = num;
        this.priority = priority;
    }
}
public class PrinterQueue1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int printNum = 0, target= 0, N = 0;
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            printNum = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            PriorityAndNum[] p = new PriorityAndNum[printNum];
            for(int j=0; j<printNum; j++) p[j] = new PriorityAndNum(j,Integer.parseInt(st.nextToken()));

        }
    }
}
