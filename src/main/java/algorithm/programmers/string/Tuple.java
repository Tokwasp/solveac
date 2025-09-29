package algorithm.programmers.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tuple {
    public int[] solution(String input) {
        List<Set<Integer>> elements = new ArrayList<>();
        String[] splits = input.substring(2, input.length() - 2)
                .replace("},{", "-")
                .split("-");

        // list 초기화
        for (int i = 0; i < splits.length; i++) {
            elements.add(new HashSet<>());
        }

        for (int i = 0; i < splits.length; i++) {
            String[] numbers = splits[i].split(",");

            for (int j = 0; j < numbers.length; j++) {
                elements.get(i).add(Integer.parseInt(numbers[j]));
            }
        }

        // 정렬
        elements.sort((a, b) -> a.size() - b.size());

        // 결과 값
        int[] result = new int[elements.size()];
        Set<Integer> numberSet = new HashSet<>();
        int setIndex = 0;

        for (int i = 0; i < elements.size(); i++) {
            Set<Integer> set = elements.get(i);

            for (int number : set) {
                if (!numberSet.contains(number)) {
                    numberSet.add(number);
                    result[setIndex] = number;
                    setIndex++;
                }
            }
        }
        return result;
    }

    public int[] solution2(String input) {
        String[] numbers = input.substring(2, input.length() - 2)
                .replace("},{", "-")
                .split("-");

        List<List<Integer>> list = new ArrayList<>();

        for(int i = 0; i < numbers.length; i++){
            String[] numberInput = numbers[i].split(",");

            list.add(new ArrayList<>());

            for(String number: numberInput){
                list.get(i).add(Integer.parseInt(number));
            }
        }

        list.sort((a,b) -> a.size() - b.size());

        Set<Integer> set = new HashSet<>();
        int[] result = new int[list.size()];
        int resultIndex = 0;

        for(int i = 0; i < list.size(); i++){
            List<Integer> numberList = list.get(i);

            for(int num :numberList){
                if(!set.contains(num)){
                    set.add(num);
                    result[resultIndex++] = num;
                }
            }
        }
        return result;
    }
}
