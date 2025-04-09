package algorithm.programmers.enterprise.kakao;

import java.util.*;

public class Compression {
    public int[] solution(String input) {
        Map<String, Integer> map = new HashMap<>();

        for(char number = 'A'; number <= 'Z'; number++){
            map.put(String.valueOf(number), number - 'A' + 1);
        }

        Queue<Character> queue = new LinkedList<>();

        for(int i = 0; i < input.length(); i++){
            queue.add(input.charAt(i));
        }

        StringBuilder dictionarySb = new StringBuilder();
        List<Integer> results = new ArrayList<>();
        int nextNumber = 27;

        while(!queue.isEmpty()){
            char ch = queue.peek();
            dictionarySb.append(ch);

            String currentString = dictionarySb.toString();
            if(map.containsKey(currentString)){
                queue.poll();
            }

            if(!map.containsKey(currentString)){
                map.put(currentString, nextNumber++);
                dictionarySb = new StringBuilder();
                results.add(
                        map.get(currentString.substring(0, currentString.length() - 1))
                );
            }
        }

        if(!dictionarySb.toString().equals("")){
            results.add(map.get(dictionarySb.toString()));
        }

        return results.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}