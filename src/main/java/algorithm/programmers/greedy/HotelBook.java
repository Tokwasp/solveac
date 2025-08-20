package algorithm.programmers.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HotelBook {
    public int solution(String[][] bookTimeInput) {
        // 입력 받기
        List<HotelBookRequest> books = new ArrayList<>();
        for (int row = 0; row < bookTimeInput.length; row++) {
            String[] bookTime = bookTimeInput[row];

            int startMinute = convertMinuteFrom(bookTime[0]);
            int endMinute = convertMinuteFrom(bookTime[1]);
            books.add(new HotelBookRequest(startMinute, endMinute));
        }

        // 시작 시간순 정렬
        books.sort(Comparator.naturalOrder());

        // 필요한 객실 수 찾기
        int max = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < books.size(); i++) {
            HotelBookRequest request = books.get(i);

            if (pq.isEmpty() || request.startMinute < pq.peek()) {
                pq.add(request.endMinute + 10);
            } else {
                pq.poll();
                pq.add(request.endMinute + 10);
            }

            max = Math.max(max, pq.size());
        }
        return max;
    }

    private static int convertMinuteFrom(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

    private static class HotelBookRequest implements Comparable<HotelBookRequest> {
        private int startMinute;
        private int endMinute;

        public HotelBookRequest(int startMinute, int endMinute) {
            this.startMinute = startMinute;
            this.endMinute = endMinute;
        }

        public int compareTo(HotelBookRequest another) {
            return this.startMinute - another.startMinute;
        }
    }
}
