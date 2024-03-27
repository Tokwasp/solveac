package string;

import java.io.*;
import java.util.*;

public class NoHeardNoSee1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<String> noHearList = new ArrayList<>();
        List<String> noSeeList = new ArrayList<>();

        for(int i=0; i<N; i++) noHearList.add(br.readLine());
        for(int i=0; i<M; i++) noSeeList.add(br.readLine());

        Map<String,Integer> map = new HashMap<>();
        for(int i=0; i<N; i++) map.put(noHearList.get(i),1);
        for(int i=0; i<M; i++)
            map.put(noSeeList.get(i),map.getOrDefault(noSeeList.get(i),0)+1);

       Set<String> set = map.keySet();
        int count = 0;
        List<String> resultList = new ArrayList();
        for (String key : set) {
            if(map.get(key) >= 2){
                resultList.add(key);
                count++;
            }
        }
        Collections.sort(resultList);
        sb.append(count).append("\n");
        for (String str : resultList) {
            sb.append(str).append("\n");
        }
        System.out.print(sb);
    }
}
