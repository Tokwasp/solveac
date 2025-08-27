package algorithm.programmers.greedy;

public class MagicElevator {
    public int solution(int num) {
        int count = 0;

        while (num != 0) {
            int divide = num % 10;

            if (divide <= 4) {
                count += divide;
                num -= divide;
            } else if (divide == 5) {
                if (num / 10 % 10 <= 4) {
                    count += divide;
                    num -= divide;
                } else {
                    count += divide;
                    num += divide;
                }
            } else {
                count += 10 - divide;
                num += 10 - divide;
            }

            num /= 10;
        }

        return count;
    }
}
