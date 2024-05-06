package algorithm.swea.d3;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Flatten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        for(int j=1; j<11; j++) {

            int dump = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();

            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i : arr) list.add(i);

            for (int i = 0; i < dump; i++) {

                int min = list.stream().mapToInt(Integer::intValue).min().getAsInt();
                int max = list.stream().mapToInt(Integer::intValue).max().getAsInt();
                int minIndex = list.indexOf(min);
                int maxIndex = list.indexOf(max);
                list.set(minIndex, (list.get(minIndex)) + 1);
                list.set(maxIndex, (list.get(maxIndex)) - 1);

            }

            int min = list.stream().mapToInt(Integer::intValue).min().getAsInt();
            int max = list.stream().mapToInt(Integer::intValue).max().getAsInt();
            String st = "#" + j + " ";
            sb.append(st).append(max - min).append("\n");
        }
        System.out.print(sb);
    }
}
