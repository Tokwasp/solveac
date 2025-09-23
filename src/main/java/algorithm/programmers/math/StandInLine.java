package algorithm.programmers.math;

public class StandInLine {
    public int[] solution(int n, long k) {
        int[] remember = new int[n];
        boolean[] visited = new boolean[n + 1];

        k -= 1;
        for (int index = 0; index < n - 1; index++) {
            long divideNum = getFactorial(n, index);
            int passIndex = (int) (k / divideNum);

            int nextNumber = -1;
            int count = 0;
            for (int i = 1; i <= visited.length - 1; i++) {
                if (!visited[i]) {
                    if (count == passIndex) {
                        nextNumber = i;
                        break;
                    }
                    count++;
                }
            }

            remember[index] = nextNumber;
            visited[nextNumber] = true;
            k %= divideNum;
        }

        for (int i = 1; i <= visited.length - 1; i++) {
            if (!visited[i]) {
                remember[n - 1] = i;
                break;
            }
        }
        return remember;
    }

    private static long getFactorial(int n, int index) {
        int startNum = n - index - 1;
        long result = 1L;

        while (startNum != 1) {
            result *= startNum;
            startNum--;
        }
        return result;
    }
}
