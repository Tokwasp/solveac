package algorithm.programmers.implement;

import java.util.ArrayList;
import java.util.List;

public class TakeOutDeliveryBox {
    public int solution(int boxCount, int rowLength, int target) {
        // 박스 인덱스 찾기
        int targetRow = (target - 1) / rowLength;

        int remain = (target - 1) % rowLength;
        int targetCol = targetRow % 2 == 0 ? remain : rowLength - 1 - remain;

        // 다음 층으로 올라가기 위해서 번호를 얼만큼 더해야 하는가?
        List<Integer> sumList = new ArrayList<>();
        int initNum = 1;
        sumList.add(initNum);

        for (int i = 1; i < rowLength; i++) {
            initNum += 2;
            sumList.add(initNum);
        }

        // 박스 치우기
        int row = targetRow;
        int count = 0;

        while (target <= boxCount) {
            if (row % 2 == 1) {
                target += sumList.get(targetCol);
            } else {
                target += sumList.get(rowLength - 1 - targetCol);
            }
            row++;
            count++;
        }

        return count;
    }
}
