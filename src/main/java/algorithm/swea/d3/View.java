package algorithm.swea.d3;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
    현재 빌딩의 각 왼쪽 2개 오른쪽 2개 총 4개의 빌딩 중 가장 높은 빌딩을 현재 빌딩 에서  뺀것이 조망이 확보된 수이다.
 */
public class View {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int j=1; j<11; j++) {

            int buildingCount = Integer.parseInt(br.readLine());
            List<Integer> buildingList = new ArrayList<>();
            int[] buildingArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            addTwoZeroHeightBuilding(buildingList);
            for (int building : buildingArr) {
                buildingList.add(building);
            }
            addTwoZeroHeightBuilding(buildingList);

            int result = 0;
            for (int i = 2; i < buildingList.size() - 2; i++) {

                int building = buildingList.get(i);
                int leftMax = Math.max(buildingList.get(i - 2), buildingList.get(i - 1));
                int rightMax = Math.max(buildingList.get(i + 2), buildingList.get(i + 1));
                int max = Math.max(leftMax, rightMax);

                if (building > max) result += building - max;

            }

            String st = "#" + j + " " + result;
            sb.append(st).append("\n");

        }
        System.out.print(sb);
    }

    static void addTwoZeroHeightBuilding(List<Integer> list){
        list.add(0);
        list.add(0);
    }
}
