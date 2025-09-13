package algorithm.programmers.binarysearch;

public class PredictedBracket {
    public int solution(int n, int a, int b) {
        // 반씩 나누기
        int count = 0;
        while (a != b) {
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            count++;
        }
        return count;
    }
}
