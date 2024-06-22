package algorithm;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class BalloonFit11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // arr 풍선의 높이 받기
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // map -> (key : 풍선의 높이 , value : 쏠 수 있는 화살의 개수)
        Map<Integer, Integer> map = new HashMap<>();

        int arrow = 0;
        // arr 크기 만큼 반복
        for(int i = 0; i < N; i++){
            int heightArrow = arr[i];
            int nextHeightArrow = arr[i] - 1;

            // 해당 풍선을 쏠 수 있을 경우
            if(map.containsKey(heightArrow)){
                int heightArrowCount = map.get(heightArrow) - 1;

                if(heightArrowCount == 0) {
                    map.remove(heightArrow);
                }
                else map.put(heightArrow, heightArrowCount);

                boolean error = nextHeightArrow < 1;
                if(!error) map.put(nextHeightArrow, map.getOrDefault(nextHeightArrow, 0) + 1);
            }
            // 풍선을 쏠 수 없는 경우
            else{
                if(nextHeightArrow >= 1) map.put(nextHeightArrow, map.getOrDefault(nextHeightArrow, 0) + 1);
                arrow += 1;
            }
        }
        System.out.println(arrow);
    }
}