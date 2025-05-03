package algorithm.programmers.enterprise.kakao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CandidateKey {
    static int keyCount = 0;
    static List<Integer> selectList;
    static List<Set<Integer>> minimalitySet;

    public int solution(String[][] relation) {
        selectList = new ArrayList<>();
        minimalitySet = new ArrayList<>();

        int attributeCol = relation[0].length;
        findCombination(0, attributeCol, relation);

        return keyCount;
    }

    static void findCombination(int depth, int target, String[][] relation){
        if(depth == target){
            if(!selectList.isEmpty() && isSatisfyUniqueness(relation) && isMinimality()){
                minimalitySet.add(new HashSet<>(selectList));
                keyCount++;
            }
            return;
        }

        // 선택x
        findCombination(depth + 1, target, relation);

        // 선택o
        selectList.add(depth);
        findCombination(depth + 1, target, relation);
        selectList.remove(selectList.size() - 1);
    }

    static boolean isSatisfyUniqueness(String[][] relation){
        Set<String> minimalSet = new HashSet<>();

        for(int row = 0; row < relation.length; row++){
            StringBuilder sb = new StringBuilder();
            for(int col : selectList){
                sb.append(relation[row][col] + " ");
            }

            String result = sb.toString();
            if(minimalSet.contains(result)){
                return false;
            }
            minimalSet.add(result);
        }
        return true;
    }

    static boolean isMinimality(){
        Set<Integer> CurrentCombination = new HashSet<>(selectList);
        for(Set<Integer> combination: minimalitySet){
            if(CurrentCombination.containsAll(combination)){
                return false;
            }
        }
        return true;
    }
}
