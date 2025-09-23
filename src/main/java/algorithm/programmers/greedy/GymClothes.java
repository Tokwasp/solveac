package algorithm.programmers.greedy;

public class GymClothes {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] arr = new int[n + 1];

        for (int i = 0; i < lost.length; i++) {
            int index = lost[i];
            arr[index] -= 1;
        }

        for (int i = 0; i < reserve.length; i++) {
            int index = reserve[i];
            arr[index] += 1;
        }

        for (int i = n; i >= 0; i--) {
            int remain = arr[i];

            if (remain == 1) {
                // 상단 도움
                if (i + 1 <= n && arr[i + 1] == -1) {
                    arr[i + 1] += 1;
                    arr[i] -= 1;
                    continue;
                }

                // 하단 도움
                if (i - 1 >= 1 && arr[i - 1] == -1) {
                    arr[i - 1] += 1;
                    arr[i] -= 1;
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (arr[i] >= 0) count++;
        }
        return count;
    }
}
