package algorithm.programmers.enterprise.kakao;

import java.util.*;

public class ParkingFeeCalculate {
    public final int MAX_PARK_OUT_MINUTE = 1439; // 23:59

    public int[] solution(int[] fees, String[] records) {
        Fee fee = new Fee(fees[0], fees[1], fees[2], fees[3]);
        Map<Integer, Integer> carInTime = new HashMap<>();
        Map<Integer, Integer> carTotalTime = new HashMap<>();
        Set<Integer> carNumberSet = new HashSet<>();

        for (String record : records) {
            String[] split = record.split(" ");
            int minute = timeToMinute(split[0]);
            int carNumber = Integer.parseInt(split[1]);
            boolean isIn = split[2].equals("IN");

            carNumberSet.add(carNumber);

            if (isIn) {
                carInTime.put(carNumber, minute);
            } else {
                int inMinute = carInTime.remove(carNumber);
                int totalTime = minute - inMinute;
                carTotalTime.put(carNumber,
                        carTotalTime.getOrDefault(carNumber, 0) + totalTime);
            }
        }

        // 아직 출차되지 않은 차량들 → 23:59 기준 출차 처리
        for (Map.Entry<Integer, Integer> entry : carInTime.entrySet()) {
            int carNumber = entry.getKey();
            int inMinute = entry.getValue();
            int totalTime = MAX_PARK_OUT_MINUTE - inMinute;
            carTotalTime.put(carNumber,
                    carTotalTime.getOrDefault(carNumber, 0) + totalTime);
        }

        // 차량 번호 오름차순 정렬 후 요금 계산
        List<Integer> carNumbers = new ArrayList<>(carNumberSet);
        Collections.sort(carNumbers);

        int[] answer = new int[carNumbers.size()];
        for (int i = 0; i < carNumbers.size(); i++) {
            int totalTime = carTotalTime.getOrDefault(carNumbers.get(i), 0);
            answer[i] = calculateFee(totalTime, fee);
        }

        return answer;
    }

    static int calculateFee(int totalTime, Fee fee) {
        if (totalTime <= fee.basicMinute) return fee.basicFee;

        int extraTime = totalTime - fee.basicMinute;
        int units = (int) Math.ceil((double) extraTime / fee.unitMinute);
        return fee.basicFee + units * fee.unitFee;
    }

    static int timeToMinute(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        return hour * 60 + minute;
    }

    static class Fee {
        int basicMinute;
        int basicFee;
        int unitMinute;
        int unitFee;

        public Fee(int basicMinute, int basicFee, int unitMinute, int unitFee) {
            this.basicMinute = basicMinute;
            this.basicFee = basicFee;
            this.unitMinute = unitMinute;
            this.unitFee = unitFee;
        }
    }
}
