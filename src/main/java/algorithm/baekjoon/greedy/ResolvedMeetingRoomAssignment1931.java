package algorithm.baekjoon.greedy;

import java.io.*;
import java.util.*;

public class ResolvedMeetingRoomAssignment1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int meetingCount = Integer.parseInt(br.readLine());

        // 입력
        List<Meeting> meetings = new ArrayList<>();
        for(int i = 0; i < meetingCount; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(start,end));
        }

        // 정렬
        meetings.sort((Meeting meeting1,Meeting meeting2) -> {
            int endResult = meeting1.end - meeting2.end;
            if(endResult != 0) return endResult;
            return meeting1.start - meeting2.start;
        });

        // 강의실 최대 이용 수
        Queue<Meeting> queue = new LinkedList<>(meetings);
        int count = 0;
        int endTime = 0;
        while(!queue.isEmpty()){
            Meeting curMeeting = queue.poll();
            if(curMeeting.start >= endTime){
                count++;
                endTime = curMeeting.end;
            }
        }
        System.out.print(count);
    }

    static class Meeting{
        private int start;
        private int end;

        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}