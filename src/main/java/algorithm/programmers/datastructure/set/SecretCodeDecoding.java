package algorithm.programmers.datastructure.set;

import java.util.HashSet;
import java.util.Set;

public class SecretCodeDecoding {

    private static final int START_NUMBER = 1;
    private static final int TARGET_DEPTH = 5;

    private static Set<Integer> numberSet;
    private static int[][] queue;
    private static int[] answer;
    private static int result = 0;

    public int solution(int maxNumber, int[][] queue, int[] answer) {
        this.numberSet = new HashSet();
        this.queue = queue;
        this.answer = answer;

        dfs(0, START_NUMBER, maxNumber);
        return result;
    }

    private static void dfs(int depth, int startNum, int endNum){
        if(depth == TARGET_DEPTH){
            if(checkCondition()){
                result++;
            }
            return;
        }

        for(int num = startNum; num <= endNum; num++){
            numberSet.add(Integer.valueOf(num));
            dfs(depth + 1, num + 1, endNum);
            numberSet.remove(Integer.valueOf(num));
        }
    }

    private static boolean checkCondition(){
        for(int row = 0; row < queue.length; row++){
            int correctNum = 0;

            for(int col = 0; col < queue[row].length; col++){
                if(numberSet.contains(queue[row][col])){
                    correctNum++;
                }
            }

            if(correctNum != answer[row]){
                return false;
            }
        }
        return true;
    }
}
