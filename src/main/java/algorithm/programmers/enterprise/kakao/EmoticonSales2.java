package algorithm.programmers.enterprise.kakao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmoticonSales2 {
    private static final int[] DISCOUNT_PERCENT = {10, 20, 30, 40};

    private static int[] remember;
    private static int[] emoticons;
    private static int[][] users;

    private static List<Result> results;

    public int[] solution2(int[][] users, int[] emoticons) {
        this.emoticons = emoticons;
        this.users = users;
        this.remember = new int[emoticons.length];
        this.results = new ArrayList<>();

        dfs(0, emoticons.length);

        results.sort(Comparator.naturalOrder());
        Result result = results.get(0);
        return new int[]{result.purchaseCount, result.totalPrice};
    }

    private static void dfs(int depth, int targetDepth) {
        if (depth == targetDepth) {
            // 이모티콘 구매
            results.add(getPurchaseResult());
            return;
        }

        for (int i = 0; i < DISCOUNT_PERCENT.length; i++) {
            remember[depth] = DISCOUNT_PERCENT[i];
            dfs(depth + 1, targetDepth);
        }
    }

    private static Result getPurchaseResult() {
        int purchaseCount = 0;
        int resultPrice = 0;

        // 사람들 순회
        for (int row = 0; row < users.length; row++) {
            int totalPrice = 0;
            int percent = users[row][0];
            int plusPrice = users[row][1];

            // 이모티콘 구매 결정
            for (int index = 0; index < emoticons.length; index++) {
                int discountPercent = remember[index];
                int basicPrice = emoticons[index];

                // 구매 하는 이모티콘 할인 비율이라면
                if (discountPercent >= percent) {
                    int discountPrice = (int) (basicPrice * (100 - discountPercent) * 0.01);
                    totalPrice += discountPrice;
                }

                // 플러스 구매 가능하면 이모티콘 구매 중단
                if (totalPrice >= plusPrice) {
                    purchaseCount++;
                    totalPrice = 0;
                    break;
                }
            }
            // 구매 비용 누적
            resultPrice += totalPrice;
        }
        return new Result(purchaseCount, resultPrice);
    }

    static class Result implements Comparable<Result> {
        private int purchaseCount;
        private int totalPrice;

        public Result(int purchaseCount, int totalPrice) {
            this.purchaseCount = purchaseCount;
            this.totalPrice = totalPrice;
        }

        public int compareTo(Result another) {
            int result = another.purchaseCount - this.purchaseCount;
            if (result != 0) return result;
            return another.totalPrice - this.totalPrice;
        }
    }
}
