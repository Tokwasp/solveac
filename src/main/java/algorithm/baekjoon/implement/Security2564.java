package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Security2564 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] widthAndHeight = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int width = widthAndHeight[0]; int height = widthAndHeight[1];
        int n = Integer.parseInt(br.readLine());

        int totalLength = width * 2 + height * 2;
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<=n; i++) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(input[0] == 1) list.add(width + height + width - input[1]);
            else if(input[0] == 2) list.add(input[1]);
            else if(input[0] == 3) list.add(width * 2 + height + input[1]);
            else if(input[0] == 4) list.add(width + height-input[1]);
        }

        int me = list.get(n); list.remove(n);
        int sum = 0;

        for(int i : list){
            int distance = Math.abs(me - i);
            int overDistance = 0;
            if(i < me) overDistance = totalLength - me + i;
            else if(i > me) overDistance = me + totalLength - i;
            int min = Math.min(distance, overDistance);
            sum += min;
        }
        System.out.print(sum);
    }
}
