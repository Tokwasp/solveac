package algorithm.programmers.string;

public class NewIdRecommand {
    public String solution(String newId) {
        // 1단계
        newId = newId.toLowerCase();

        // 2단계
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newId.length(); i++) {
            char ch = newId.charAt(i);
            if (('a' <= ch && ch <= 'z') || (ch == '-' || ch == '_' || ch == '.') ||
                    ('0' <= ch && ch <= '9')) {
                sb.append(ch);
            }
        }
        newId = sb.toString();

        // 3단계
        sb = new StringBuilder();
        char prev = 'a';
        for (int i = 0; i < newId.length(); i++) {
            char ch = newId.charAt(i);
            if (prev == '.' && ch == '.') continue;
            prev = ch;
            sb.append(String.valueOf(ch));
        }
        newId = sb.toString();

        // 4단계
        if (newId.length() != 0) {
            if (newId.charAt(0) == '.') {
                newId = newId.substring(1, newId.length());
            }
        }
        if (newId.length() != 0) {
            if (newId.charAt(newId.length() - 1) == '.') {
                newId = newId.substring(0, newId.length() - 1);
            }
        }

        // 5단계
        if (newId.length() == 0) {
            newId = "a";
        }

        // 6단계
        if (newId.length() >= 16) {
            newId = newId.substring(0, 15);

            if (newId.charAt(newId.length() - 1) == '.') {
                newId = newId.substring(0, newId.length() - 1);
            }
        }

        // 7단계
        int count = newId.length();
        String last = String.valueOf(newId.charAt(count - 1));
        while (count < 3) {
            newId += last;
            count++;
        }
        return newId;
    }
}
