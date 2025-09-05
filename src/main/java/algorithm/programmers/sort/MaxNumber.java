package algorithm.programmers.sort;

import java.util.ArrayList;
import java.util.List;

public class MaxNumber {
    public String solution(int[] numbers) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            list.add(String.valueOf(numbers[i]));
        }

        list.sort((a, b) -> (b + a).compareTo(a + b));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            sb.append(list.get(i));
        }

        String result = sb.toString();
        if (result.charAt(0) == '0') return "0";
        return result;
    }
}
