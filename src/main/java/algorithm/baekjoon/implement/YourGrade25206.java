package algorithm.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class YourGrade25206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        double total = 0, hakSum = 0;
        Map<String,Double> map = new HashMap<>();
        map.put("A+", 4.5);  map.put("A0", 4.0);  map.put("B+", 3.5);  map.put("B0", 3.0);
        map.put("C+", 2.5);  map.put("C0", 2.0);  map.put("D+", 1.5);  map.put("D0", 1.0);
        map.put("F", 0.0); map.put("P",100.0);

        for(int i=0; i<20; i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            double hak = Double.parseDouble(st.nextToken());
            double grade = map.get(st.nextToken());
            if(grade != 100){
                hakSum += hak;
                total += hak * grade;
            }
        }
        total /= hakSum;
        System.out.printf("%.6f",total);
    }
}
