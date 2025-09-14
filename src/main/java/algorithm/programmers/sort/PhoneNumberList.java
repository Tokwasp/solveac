package algorithm.programmers.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PhoneNumberList {
    public boolean solution(String[] phoneBooks) {
        Set<String> set = new HashSet<>();

        // 공백 제거
        for (int i = 0; i < phoneBooks.length; i++) {
            phoneBooks[i] = phoneBooks[i].replace(" ", "");
        }

        // 길이순 정렬
        Arrays.sort(phoneBooks, (a, b) -> a.length() - b.length());

        for (int i = 0; i < phoneBooks.length; i++) {
            String book = phoneBooks[i];
            int length = book.length();
            if (set.contains(book)) {
                return false;
            }

            // 접두어 확인
            for (int j = 0; j < 20; j++) {
                if (length <= j) break;

                String sub = book.substring(0, j + 1);
                if (set.contains(sub)) {
                    return false;
                }
            }
            set.add(book);
        }
        return true;
    }

    // 풀이2 사전순 정렬
    public boolean solution2(String[] phoneBooks) {
        Arrays.sort(phoneBooks);

        for(int i = 0; i < phoneBooks.length - 1; i++){
            String number = phoneBooks[i];
            String next = phoneBooks[i + 1];

            if(next.startsWith(number)){
                return false;
            }
        }
        return true;
    }
}
