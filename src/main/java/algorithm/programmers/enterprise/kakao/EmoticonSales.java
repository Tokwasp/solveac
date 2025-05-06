package algorithm.programmers.enterprise.kakao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmoticonSales {
    static final int[] salePersents = new int[] {10,20,30,40};

    static List<Result> results;
    static int[] emoticonSales;
    static int[][] users;
    static int[] emoticons;

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        emoticonSales = new int[emoticons.length];
        results = new ArrayList<>();

        findPermutation(0, emoticons.length);
        Collections.sort(results);
        Result result = results.get(0);
        return new int[] {result.plusSubScribeCount, result.emoticonSalesTotalPrice};
    }

    public static void findPermutation(int depth, int targetDepth){
        if(depth == targetDepth){
            userBuyEmoticons();
            return;
        }

        for(int i = 0; i < salePersents.length; i++){
            emoticonSales[depth] = salePersents[i];
            findPermutation(depth + 1, targetDepth);
        }
    }

    public static void userBuyEmoticons(){
        int[] userBuyTotalPrices = new int[users.length];

        for(int i = 0; i < users.length; i++){
            int userBuyPercent = users[i][0];

            for(int j = 0; j < emoticonSales.length; j++){
                int emoticonSale = emoticonSales[j];

                if(userBuyPercent <= emoticonSale){
                    int buyPrice = (int)(emoticons[j] * ((100 - emoticonSale) * 0.01));
                    userBuyTotalPrices[i] += buyPrice;
                }
            }
        }

        int plusSubScribeCount = 0;
        int emoticionSalesTotalPrice = 0;

        for(int i = 0; i < userBuyTotalPrices.length; i++){
            int plusBuyPrice = users[i][1];
            int emoticonBuyPrice = userBuyTotalPrices[i];

            if(plusBuyPrice <= emoticonBuyPrice){
                plusSubScribeCount++;
            } else{
                emoticionSalesTotalPrice += emoticonBuyPrice;
            }
        }

        results.add(new Result(plusSubScribeCount, emoticionSalesTotalPrice));
    }

    static class Result implements Comparable<Result> {
        private int plusSubScribeCount;
        private int emoticonSalesTotalPrice;

        public Result(int plusSubScribeCount,int emoticonSalesTotalPrice){
            this.plusSubScribeCount = plusSubScribeCount;
            this.emoticonSalesTotalPrice = emoticonSalesTotalPrice;
        }

        public int compareTo(Result result){
            int compareResult = result.plusSubScribeCount - this.plusSubScribeCount;
            if(compareResult != 0) return compareResult;
            return result.emoticonSalesTotalPrice - this.emoticonSalesTotalPrice;
        }
    }
}
