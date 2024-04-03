package greedy;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Assignment1931 {
    static class Conference{
        int start;
        int end;

        public Conference(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getEnd() {
            return end;
        }

        public int getStart() {
            return start;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Conference> list = new ArrayList<>();

        for(int i=0; i<n; i++){
            String[] str = br.readLine().split(" ");
            int[] input = Stream.of(str).mapToInt(Integer::parseInt).toArray();
            list.add(new Conference(input[0],input[1]));
        }

        Collections.sort(list,Comparator.comparingInt(Conference::getEnd).thenComparing(Conference::getStart));

        int count = 1;
        int compareStart = list.get(0).getStart();
        int compareEnd = list.get(0).getEnd();

        for(int i=1; i<list.size(); i++){
            int start = list.get(i).getStart();
            int end = list.get(i).getEnd();

            if(start >= compareEnd){
                compareEnd = end;
                compareStart = start;
                count++;
            }
        }
        System.out.print(count);
    }
}
