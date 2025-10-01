package algorithm.programmers.enterprise.kakao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShuttleBus {
    private static final int FIRST_MINUTE = 540;
    private static final int LAST_MINUTE = 1140;

    public String solution(int totalCount, int dist, int maxPersonCount, String[] timetables) {
        Queue<Integer> queue = new LinkedList<>();

        int[] minutes = convertTimeFrom(timetables);
        Arrays.sort(minutes);

        for (int i = 0; i < minutes.length; i++) {
            int minute = minutes[i];
            if (minute > LAST_MINUTE) {
                break;
            }
            queue.add(minute);
        }

        int goMinute = FIRST_MINUTE;

        // 마지막 셔틀 전까지 탑승
        for (int t = 1; t < totalCount; t++) {

            // 인원 탑승
            int count = 0;
            while (!queue.isEmpty() && goMinute >= queue.peek() && count < maxPersonCount) {
                queue.poll();
                count++;
            }
            goMinute += dist;
        }

        // 마지막 셔틀
        int myMinute = 0;
        if (queue.isEmpty() || queue.size() < maxPersonCount) {
            myMinute = goMinute;
        }

        if (queue.size() >= maxPersonCount) {
            int count = 0;
            while (count != maxPersonCount - 1) {
                queue.poll();
                count++;
            }
            myMinute = queue.poll() - 1;
        }

        return convertHourString(myMinute);
    }

    private int[] convertTimeFrom(String[] timetables) {
        int[] minutes = new int[timetables.length];

        for (int i = 0; i < timetables.length; i++) {
            String[] input = timetables[i].split(":");
            minutes[i] = Integer.parseInt(input[0]) * 60 + Integer.parseInt(input[1]);
        }
        return minutes;
    }

    private String convertHourString(int minute) {
        StringBuilder sb = new StringBuilder();
        int hour = minute / 60;
        minute = minute % 60;

        if (hour < 10) {
            sb.append("0");
        }
        sb.append(hour).append(":");

        if (minute < 10) {
            sb.append("0");
        }
        sb.append(minute);
        return sb.toString();
    }
}
