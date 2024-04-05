package algorithm.string;

import java.io.*;
import java.util.*;

public class WordSort1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++) set.add(br.readLine());
        List<String> list = new ArrayList(set);

        list.sort((o1, o2) -> {
            //길이 비교
            if(o1.length() < o2.length())
                return -1;
            else if(o1.length() > o2.length())
                return 1;
            //사전순 정렬
            else{
                int idx = 0;
                while(true){
                    if(o1.charAt(idx) == o2.charAt(idx)) idx++;
                    else break;
                }
                return o1.charAt(idx) - o2.charAt(idx);
            }
        });

        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }
}
