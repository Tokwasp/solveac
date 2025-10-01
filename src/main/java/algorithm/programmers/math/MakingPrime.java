package algorithm.programmers.math;

import java.util.Arrays;

public class MakingPrime {
    public int solution(int[] nums) {
        int count = 0;
        int[] remember = new int[3];

        for (int num1 = 0; num1 < nums.length; num1++) {
            remember[0] = nums[num1];

            for (int num2 = num1 + 1; num2 < nums.length; num2++) {
                remember[1] = nums[num2];

                for (int num3 = num2 + 1; num3 < nums.length; num3++) {
                    remember[2] = nums[num3];

                    int sum = Arrays.stream(remember).sum();
                    if (isPrime(sum)) count++;
                }
            }
        }
        return count;
    }

    private boolean isPrime(int num) {
        if (num == 0 || num == 1) return false;
        int sqrt = (int) Math.sqrt(num);

        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
