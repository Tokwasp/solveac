package algorithm.programmers.implement;

public class FlexibleWorkSystem {
    private static final int SATUR_DAY = 5;
    private static final int SUN_DAY = 6;

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int initStartDay = startday - 1;
        int count = 0;

        for (int person = 0; person < schedules.length; person++) {
            // 날짜 초기화
            startday = initStartDay;

            // 제한 시간
            int limitTime = schedules[person] + 10;
            int[] timelog = timelogs[person];

            boolean pass = true;

            // 출근 체크
            for (int day = 0; day < timelog.length; day++) {
                int destinationTime = timelog[day];
                startday = startday % 7;

                // 주말이면 패스
                if (startday == SATUR_DAY || startday == SUN_DAY) {
                    startday++;
                    continue;
                }

                // 분으로 변경
                int limitMinute = convertMinuteFrom(limitTime);
                int destinationMinute = convertMinuteFrom(destinationTime);

                // 지각이면 제외
                if (limitMinute < destinationMinute) {
                    pass = false;
                    break;
                }

                startday++;
            }

            // 지각 아니면 상품
            if (pass) count++;
        }

        return count;
    }

    private static int convertMinuteFrom(int time) {
        int hour = time / 100;
        int minute = time % 100;

        return hour * 60 + minute;
    }
}
