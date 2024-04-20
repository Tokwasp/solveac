package algorithm.simulation;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class CandidateRecommend1713 {
    static class Candidate implements Comparable {
        private int CandidateNum;
        private int recommendNum;

        public int getRecommendNum() {
            return recommendNum;
        }

        public Candidate(int candidateNum, int recommendNum) {
            this.CandidateNum = candidateNum;
            this.recommendNum = recommendNum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Candidate candidate = (Candidate) o;
            return CandidateNum == candidate.CandidateNum;
        }

        @Override
        public int compareTo(Object o) {
            return this.CandidateNum - ((Candidate)o).CandidateNum;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int recommendNum = Integer.parseInt(br.readLine());

        ArrayList<Candidate> list = new ArrayList<Candidate>();

        int[] recommendArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < recommendArr.length; i++) {
            int recommend = recommendArr[i];
            Candidate candidate = new Candidate(recommend,1);
            //indexof , contains 메소드는 equals를 오버라이딩 해야한다.
            int index = list.indexOf(candidate);

            //액자가 가득찼을 경우
            if(list.size() == n){
                //액자에 추천후보가 있을경우
                if(index != -1){
                    list.get(index).recommendNum++;
                }
                //없을 경우
                else{
                    int min = list.stream().mapToInt(Candidate::getRecommendNum).min().getAsInt();
                    for(Candidate candi : list){
                        if(candi.recommendNum == min) {
                            list.remove(candi);
                            break;
                        }
                    }
                    list.add(candidate);
                }
            }
            //액자가 가득차지 않았을 경우
            else {
                //액자에 후보가 있을 경우
                if (index != -1) list.get(index).recommendNum++;
                //없을 경우
                else list.add(candidate);
            }
        }
        //comparable 오버 라이딩 해야 한다.
        Collections.sort(list);
        for (Candidate candidate : list) {
            System.out.print(candidate.CandidateNum + " ");
        }
    }
}
