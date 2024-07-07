package algorithm.baekjoon.greedy;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class TimeManage1263 {
    static class Work{
        int workTime;
        int endTime;

        public Work(int workTime, int endTime) {
            this.workTime = workTime;
            this.endTime = endTime;
        }

        public int getEndTime() {
            return endTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Work> list = new ArrayList<>();

        // 일하는 시간, 끝나는 시간 List 입력
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            list.add(new Work(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        // 끝나는 시간 순으로 오름 차순 정렬
        list.sort(Comparator.comparingInt(Work::getEndTime));

        // 끝나는 시간의 최대값
        int endTimeMax = 0;
        // 일한 시간의 총합
        int totalWorkTime = 0;
        // 일을 시작 해야 하는 시간
        int minTime = Integer.MAX_VALUE;
        // 시작 시간이 - 인 경우
        boolean check = false;

        for(int i = 0; i < N; i++){
            Work work = list.get(i);
            int endTime = work.endTime; int workTime = work.workTime;

            totalWorkTime += workTime;
            endTimeMax = Math.max(endTimeMax, endTime);

            if(endTimeMax - totalWorkTime < 0){
                System.out.println("-1");
                check = true;
                break;
            }
            minTime = Math.min(minTime,endTimeMax - totalWorkTime);
        }
        if(!check) System.out.println(minTime);
    }
}
