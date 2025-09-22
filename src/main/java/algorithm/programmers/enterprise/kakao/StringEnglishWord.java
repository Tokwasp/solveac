package algorithm.programmers.enterprise.kakao;

public class StringEnglishWord {
    public int solution(String input) {
        String[] arr = new String[]{"zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"};

        int index = 0;
        StringBuilder sb = new StringBuilder();

        while (index <= input.length() - 1) {
            char ch = input.charAt(index);

            if ('0' <= ch && ch <= '9') {
                sb.append(ch);
                index++;
                continue;
            }

            StringBuilder buffer = new StringBuilder();

            while (index <= input.length() - 1 && 'a' <= input.charAt(index) && input.charAt(index) <= 'z') {
                buffer.append(input.charAt(index++));

                String bufferStr = buffer.toString();
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].equals(bufferStr)) {
                        sb.append(i);
                        buffer = new StringBuilder();
                        break;
                    }
                }
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
