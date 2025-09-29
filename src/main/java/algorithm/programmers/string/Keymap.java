package algorithm.programmers.string;

import java.util.Arrays;

public class Keymap {
    private static final int NOT_POSSIBLE_COUNT = 101;

    public int[] solution(String[] keymaps, String[] targets) {
        int[] minClick = new int['Z' - 'A' + 1];
        Arrays.fill(minClick, NOT_POSSIBLE_COUNT);

        // 입력
        for (int i = 0; i < keymaps.length; i++) {
            String keyMap = keymaps[i];

            for (int index = 0; index < keyMap.length(); index++) {
                char ch = keyMap.charAt(index);
                int curIndex = ch - 'A';
                minClick[curIndex] = Math.min(minClick[curIndex], index + 1);
            }
        }

        // 찾기
        int[] result = new int[targets.length];

        for (int i = 0; i < targets.length; i++) {
            String target = targets[i];

            int count = 0;
            for (int index = 0; index < target.length(); index++) {
                char ch = target.charAt(index);
                int curCount = minClick[ch - 'A'];

                if (curCount == NOT_POSSIBLE_COUNT) {
                    result[i] = -1;
                    break;
                }

                count += curCount;
            }

            if (result[i] != -1) {
                result[i] = count;
            }
        }

        return result;
    }
}
