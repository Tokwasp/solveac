package greedy;

import java.io.*;
import java.util.*;

public class NewRecruits1946 {
    public static class Person implements Comparable{
        int documentRank;
        int interviewRank;

        Person(int documentScore, int interviewScore){
            this.documentRank = documentScore;
            this.interviewRank = interviewScore;
        }

        @Override
        public int compareTo(Object o) {
            Person other = (Person)o;
            return Integer.compare(this.documentRank, other.documentRank);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int documentRank = 0, interviewRank = 0;
        List<Person> personList = new ArrayList<>();

        for(int i=0; i<T; i++){
            //사람의수
            int N = Integer.parseInt(br.readLine());

            //서류점수, 면접점수 입력받고 List에 넣기
            for(int j=0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                documentRank = Integer.parseInt(st.nextToken());
                interviewRank = Integer.parseInt(st.nextToken());
                personList.add(new Person(documentRank,interviewRank));
            }
            //서류 점수 순으로 오름 차순 정렬
            personList.sort(Comparator.naturalOrder());

            //면접 순위를 비교 서류1위는 통과 이므르 초기값1
            int count = 1;
            int min = personList.get(0).interviewRank;
            //면접 순위를 비교 해야 하는데 이전의 면접 순위중 제일 잘한 사람 보다 순위가 높아야 합격 한다.
            for(int k=1; k<personList.size(); k++){
                if(personList.get(k).interviewRank < min){
                    count++;
                    min = personList.get(k).interviewRank;
                }
            }
            sb.append(count).append("\n");
            personList.clear();
        }
        System.out.print(sb);
    }
}
