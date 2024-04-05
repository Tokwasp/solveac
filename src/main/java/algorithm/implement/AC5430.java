package algorithm.implement;

import java.io.*;
import java.util.*;

public class AC5430 {
    static boolean reverseMode = false;

    static void Reverse() {
        reverseMode = !reverseMode;
    }

    static int Delete(Deque<Integer> deq) {
        if (deq.isEmpty()) return -1;
        else {
            if (reverseMode) deq.pollLast();
            else deq.pollFirst();
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Deque<Integer> deq = new LinkedList<>();

        for (int i = 0; i < t; i++) {
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");

            while (st.hasMoreTokens()) {
                deq.add(Integer.parseInt(st.nextToken()));
            }

            boolean flag = false;
            reverseMode = false;

            for (int j = 0; j < command.length(); j++) {
                char ch = command.charAt(j);
                if (ch == 'R') Reverse();
                if (ch == 'D') {
                    if (Delete(deq) == -1) {
                        sb.append("error").append("\n");
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                sb.append("[");
                if (reverseMode) {
                    while (!deq.isEmpty()) {
                        if (deq.size() == 1) sb.append(deq.pollLast());
                        else sb.append(deq.pollLast()).append(",");
                    }
                } else {
                    while (!deq.isEmpty()) {
                        if (deq.size() == 1) sb.append(deq.pollFirst());
                        else sb.append(deq.pollFirst()).append(",");
                    }
                }
                sb.append("]").append("\n");
            }
        }
        System.out.print(sb);
    }
}

