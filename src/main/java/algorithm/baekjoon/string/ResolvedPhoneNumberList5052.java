package algorithm.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ResolvedPhoneNumberList5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int testNum = 0; testNum < testCaseCount; testNum++) {
            int phoneNumberCount = Integer.parseInt(br.readLine());
            String[] phoneNumbers = new String[phoneNumberCount];

            // 전화번호 입력 받기
            for (int i = 0; i < phoneNumberCount; i++) {
                phoneNumbers[i] = br.readLine();
            }

            // 전화번호 정렬
            Arrays.sort(phoneNumbers);

            // 앞뒤 번호만 비교하여 접두사 여부 확인
            boolean consistent = true;
            for (int i = 0; i < phoneNumberCount - 1; i++) {
                if (phoneNumbers[i + 1].startsWith(phoneNumbers[i])) {
                    consistent = false;
                    break;
                }
            }

            sb.append(consistent ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }
}
