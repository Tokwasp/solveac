package algorithm.programmers.datastructure.set;

import java.util.HashSet;
import java.util.Set;

public class EnglishWordChain {
    public int[] solution(int personCount, String[] words) {
        Set<String> wordSet = new HashSet<>();

        char prior = '0';
        int order = 1;
        for (int i = 0; i < words.length; i++) {
            String curWord = words[i];
            int number = (i % personCount) + 1;

            // 첫 단어
            if (prior == '0') {
                wordSet.add(curWord);
                prior = curWord.charAt(curWord.length() - 1);
                continue;
            }

            // 이미 나온 단어이거나 끝말을 안지킨 경우
            if (wordSet.contains(curWord) || curWord.charAt(0) != prior) {
                return new int[]{number, order};
            }

            // 차례
            if ((i + 1) % personCount == 0) order++;

            // 업데이트
            prior = curWord.charAt(curWord.length() - 1);
            wordSet.add(curWord);
        }

        return new int[]{0, 0};
    }
}
