package algorithm.programmers.enterprise.kakao;

import java.util.*;

public class MenuRenewal {
    static Map<String,Integer> subMap;
    static List<Character> subList;

    public String[] solution(String[] orders, int[] course) {
        subMap = new HashMap<>();

        // 정렬
        for(int index = 0; index < orders.length; index++){
            char[] charArr = orders[index].toCharArray();
            Arrays.sort(charArr);
            orders[index] = String.valueOf(charArr);
        }

        List<String> results = new ArrayList<>();
        for(int targetCount :course){
            subMap = new HashMap<>();

            // 조합 찾기
            for(String order : orders){
                subList = new ArrayList<>();
                findSub(order, targetCount, 0);
            }

            // 최대 주문 조합수 찾기
            int maxSubOrderCount = 0;
            for(Map.Entry<String,Integer> entry : subMap.entrySet()){
                maxSubOrderCount = Math.max(entry.getValue(), maxSubOrderCount);
            }

            // 최대 주문 조합 결과에 넣기
            if(maxSubOrderCount >= 2){
                for(Map.Entry<String,Integer> entry : subMap.entrySet()){
                    if(entry.getValue() == maxSubOrderCount){
                        results.add(entry.getKey());
                    }
                }
            }
        }
        Collections.sort(results);

        String[] resultArr = new String[results.size()];
        for(int i = 0; i < results.size(); i++){
            resultArr[i] = results.get(i);
        }
        return resultArr;
    }

    static void findSub(String order, int targetCount, int currentCount){
        if(subList.size() == targetCount){
            String subString = listToString();
            subMap.put(subString, subMap.getOrDefault(subString, 0) + 1);
            return;
        }

        for(int i = currentCount; i < order.length(); i++){
            subList.add(order.charAt(i));
            findSub(order, targetCount, i + 1);
            subList.remove(subList.size() - 1);
        }
    }

    static String listToString(){
        StringBuilder sb = new StringBuilder();
        for(int index = 0; index < subList.size(); index++){
            sb.append(subList.get(index));
        }
        return sb.toString();
    }
}
