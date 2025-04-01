package algorithm.programmers.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class NewsClustering {
    static Set<String> subStringSet;

    public int solution(String firstString, String secondString) {
        subStringSet = new HashSet<>();

        Map<String,Integer> firstCountingMap = getSubStringCountMap(firstString);
        Map<String,Integer> SecondCountingMap = getSubStringCountMap(secondString);

        double intersection = 0;
        double union = 0;
        for(String string : subStringSet){
            int firstSharedCount = firstCountingMap.getOrDefault(string, 0);
            int secondSharedCount = SecondCountingMap.getOrDefault(string, 0);

            intersection += Math.min(firstSharedCount, secondSharedCount);
            union += Math.max(firstSharedCount, secondSharedCount);
        }

        if(intersection == 0 && union == 0){
            return 65536;
        }

        double divide = (intersection / union);
        return (int) (divide * 65536);
    }

    static boolean isAlphabet(char ch){
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }

    static Map<String,Integer> getSubStringCountMap(String string){
        Map<String,Integer> countingMap = new HashMap<>();

        for(int i = 0; i < string.length() - 1; i++){
            char ch = string.charAt(i);
            char ch2 = string.charAt(i + 1);

            if(isAlphabet(ch) && isAlphabet(ch2)){
                StringBuilder sb = new StringBuilder();
                sb.append(ch).append(ch2);
                String sbString = sb.toString().toUpperCase();

                subStringSet.add(sbString);
                countingMap.put(sbString, countingMap.getOrDefault(sbString, 0) + 1);
            }
        }
        return countingMap;
    }
}