package algorithm.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Rainwater14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = input[0]; int col = input[1];

        int[] blockHeight = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int rain = 0;
        for(int i = 1; i < col - 1; i++){

            int curHeight = blockHeight[i];

            int leftHeightMax = 0;
            for(int j = 0; j < i; j++){
                leftHeightMax = Math.max(leftHeightMax, blockHeight[j]);
            }

            int rightHeightMax = 0;
            for(int j = i + 1; j < col; j++){
                rightHeightMax = Math.max(rightHeightMax, blockHeight[j]);
            }

            int calculateRain = Math.min(leftHeightMax, rightHeightMax) - curHeight;
            if(calculateRain > 0) rain += calculateRain;
        }
        System.out.println(rain);
    }
}
