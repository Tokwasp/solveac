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

            // 해당 풍선을 쏠 수 있을 경우
            if(map.containsKey(arr[i])){
                int remainArrowCount = map.get(arr[i]) - 1;
                int nextArrow = arr[i] - 1;

                if(remainArrowCount == 0) {
                    map.remove(arr[i]);
                }
                else map.put(arr[i], remainArrowCount);

                boolean error = nextArrow < 1;
                if(!error) map.put(nextArrow, map.getOrDefault(nextArrow, 0) + 1);
            }
            // 풍선을 쏠 수 없는 경우
            else{
                map.put(arr[i] - 1, map.getOrDefault(arr[i] - 1, 0) + 1);
                arrow += 1;
            }
        }
        System.out.println(arrow);
    }
}
