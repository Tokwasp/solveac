package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
* 가장 최근 과제를 풀어야 했다. 큐로 접근 하였 는데 가장 앞쪽에 데이터를 넣어야 해서 Deque로 바꿧다.
* 문제를 풀고 알게 된것은 Deque addFirst()만 사용 한다면, 큐를 뒤집어 스택 으로 문제를 풀어도 된다.
* */
public class HomeworkNeverEnding17952 {
    static int score = 0;

    static class Homework{
        private int score;
        private int minute;

        public Homework(int score, int minute) {
            this.score = score;
            this.minute = minute;
        }

        public int getScore() {
            return score;
        }

        public int getMinute() {
            return minute;
        }

        public int setMinute(int minute) {
            this.minute = minute;
            return minute;
        }

    }

    public static void main(String[] args) throws IOException {
        Deque<Homework> deq = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        for(int i=0; i<n; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int command = input[0];
            if(command == 0) study(deq);
            else{
                int homeworkScore = input[1]; int homeworkMinute = input[2];
                deq.offerFirst(new Homework(homeworkScore,homeworkMinute));
                study(deq);
            }
        }
        System.out.print(score);
    }

    private static void study(Deque<Homework> deq) {
        if(!deq.isEmpty()) {
            Homework homework = deq.poll();
            int minute = homework.setMinute(homework.getMinute() - 1);
            if (minute == 0) score += homework.getScore();
            else deq.offerFirst(homework);
        }
    }
}
