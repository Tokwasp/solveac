package implement;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
  풀이 :
  문제 에서 구현 하는 함수를 list 메소드를 통해 만들면 된다.
 */
public class Set11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        List list = new ArrayList();
        Integer num = 0;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if(st.hasMoreTokens()) num = Integer.valueOf(st.nextToken());

            if(order.equals("add")) if(!list.contains(num)) list.add(num);
            if(order.equals("remove")) if(list.contains(num)) list.remove(num);
            if(order.equals("check")){
                if(list.contains(num))sb.append("1").append("\n");
                else sb.append("0").append("\n");
            }
            if(order.equals("toggle")){
                if(list.contains(num)) list.remove(num);
                else list.add(num);
            }
            if(order.equals("all")) list = Stream.iterate(1, n -> n+1).limit(20).collect(Collectors.toList());
            if(order.equals("empty")) list.clear();
        }
        System.out.print(sb);
    }
}
