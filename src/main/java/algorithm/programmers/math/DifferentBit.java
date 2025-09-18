package algorithm.programmers.math;

public class DifferentBit {
    public long[] solution(long[] numbers) {
        long[] result = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];
            result[i] = convertFx(number);
        }
        return result;
    }

    private static long convertFx(long number) {
        StringBuilder sb = new StringBuilder();

        while (number != 0) {
            long remain = number % 2;
            sb.append(remain);
            number /= 2;
        }
        sb.append(0);

        String binary = sb.reverse().toString();
        int zeroIndex = -1;

        for (int i = binary.length() - 1; i >= 0; i--) {
            char ch = binary.charAt(i);

            if (ch == '0') {
                zeroIndex = i;
                break;
            }
        }

        sb = new StringBuilder(binary);
        for (int i = zeroIndex; i < binary.length(); i++) {
            char ch = binary.charAt(i);
            char next = ch == '1' ? '0' : '1';

            sb.setCharAt(i, next);
        }

        int differCount = binary.length() - zeroIndex;
        int index = binary.length() - 1;
        while (differCount > 2) {
            sb.setCharAt(index, '1');
            differCount--;
            index--;
        }

        String result = sb.toString();
        return Long.parseLong(result, 2);
    }

    // 간단한 풀이
    public long[] solution2(long[] numbers) {
        long[] result = new long[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            long number = numbers[i];
            String binary = Long.toBinaryString(number);
            char lastBit = binary.charAt(binary.length() - 1);

            if(lastBit == '0'){
                result[i] = number + 1;
                continue;
            }

            StringBuilder sb = new StringBuilder("0" + binary);
            for(int index = sb.length() - 1; index >= 0; index--){
                char bit = sb.charAt(index);
                if(bit == '0'){
                    sb.setCharAt(index, '1');
                    sb.setCharAt(index + 1, '0');
                    break;
                }
            }
            result[i] = Long.parseLong(sb.toString(), 2);
        }
        return result;
    }
}
