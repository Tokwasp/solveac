package algorithm.programmers.string;

public class StringSplit {
    public int solution(String input) {
        int count = 0;
        char prior = '0';
        int result = 0;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (count == 0) {
                prior = ch;
            }

            if (ch == prior) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                result++;
            }
        }

        if (count > 0) {
            result++;
        }
        return result;
    }
}
