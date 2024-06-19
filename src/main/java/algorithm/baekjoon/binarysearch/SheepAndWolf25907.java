package algorithm.baekjoon.binarysearch;

import java.io.*;

/*
    문제 풀이 :

    풀이 방법 : 이진 탐색
    20번 이내의 질의를 통해 무지가 출근 하는 날을 찾아야 한다.
    입력의 범위는 최대 10만 이므로 이진 탐색시 18번 내로 찾을 수 있다. (밑이 2인 log N)

 */

public class SheepAndWolf25907 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int day = Integer.parseInt(br.readLine());

        int low = 1; int high = day;

        while(low <= high){
            int mid = (low + high) / 2;
            out.println("? " + mid);
            out.flush();

            int sheepCount = Integer.parseInt(br.readLine());
            int wolfCount = mid - sheepCount;

            if(sheepCount == wolfCount){
                out.println("! " + mid);
                out.flush();
                break;
            }
            else if(sheepCount > wolfCount){
                low = mid + 1;
            }
            else if(sheepCount < wolfCount){
                high = mid - 1;
            }
        }
    }
}
