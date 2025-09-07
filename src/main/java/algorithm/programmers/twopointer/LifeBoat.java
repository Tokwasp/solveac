package algorithm.programmers.twopointer;

import java.util.Arrays;

public class LifeBoat {
    public int solution(int[] people, int limit) {
        // 오름 차순 정렬
        Arrays.sort(people);

        int start = 0;
        int end = people.length - 1;
        int count = 0;

        while (start <= end) {
            if (start == end) {
                count++;
                break;
            }

            if (people[start] + people[end] <= limit) {
                count++;
                start++;
                end--;
            } else {
                count++;
                end--;
            }
        }
        return count;
    }
}
