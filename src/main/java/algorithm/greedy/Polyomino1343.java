package algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Polyomino1343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int xCount = 0, repeat = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X')
                xCount++;
            if(input.charAt(i) == '.' || i == input.length()-1) {
                if (xCount % 4 == 1 || xCount % 4 == 3) {
                    System.out.println("-1");
                    return;
                }
                if (xCount % 4 == 0 || xCount % 4 == 2) {
                    repeat = xCount / 4;
                    sb.append(Stream.generate(() -> "AAAA").limit(repeat).collect(Collectors.joining()));
                    repeat = (xCount - repeat * 4) / 2;
                    sb.append(Stream.generate(() -> "BB").limit(repeat).collect(Collectors.joining()));
                }
                if(input.charAt(i) == '.')
                    sb.append('.');
                xCount = 0;
            }
        }
        System.out.println(sb);
    }
}
