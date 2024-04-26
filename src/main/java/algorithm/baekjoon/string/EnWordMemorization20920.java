package algorithm.baekjoon.string;

import java.io.*;
import java.util.*;

public class EnWordMemorization20920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String,Integer> map = new HashMap<>();

        for(int i=0; i<N; i++){
            String input = br.readLine();
            if(input.length() >= M) map.put(input,map.getOrDefault(input,0)+1);
        }
        List<String> list = new ArrayList<>(map.keySet());
        list.sort((String o1, String o2) -> {
            // 반복된 문자
            if(map.get(o1) > map.get(o2))
                return -1;
            else if(map.get(o1) < map.get(o2))
                return 1;
            // 문자 반복횟수가 같을 경우 2번 String의 길이 정렬
            else{
                if(o1.length() > o2.length())
                    return -1;
                else if(o1.length() < o2.length())
                    return 1;
                // String의 길이가 같으면 3번 문자의 사전 순서 정렬
                else{
                    int idx = 0;
                    while(true){
                        if(o1.charAt(idx) == o2.charAt(idx)) idx++;
                        else break;
                    }
                    return o1.charAt(idx) - o2.charAt(idx);
                }
            }
        });
        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }
}
