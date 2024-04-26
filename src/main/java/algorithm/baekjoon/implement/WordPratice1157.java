package algorithm.baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordPratice1157 {
    public static void main(String[] args) throws IOException {
        //입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().toUpperCase();

        //입력 받은것을 list add
        List<String> inputList = new ArrayList<>();
        Collections.addAll(inputList, input.split(""));

        //정렬하기
        Collections.sort(inputList);

        String maxString = "";
        String BeforeString ="";
        int StringMaxNumber = 0;
        int StringNumber = 0;

        //1개일 경우 1개가 최댓값
        if(inputList.size() == 1) maxString = inputList.get(0);

        //이전의 문자를 저장하고 이전문자와 같은지 체크한다. 그후 가장 많은 문자와 개수를 비교하여 현재의 문자가 더많다면 max값을 변경한다.
        for(int i=0; i<inputList.size(); i++){
            if(inputList.get(i).equals(BeforeString)) StringNumber++;
            else {
                BeforeString = inputList.get(i);
                StringNumber = 1;
            }
            if(StringMaxNumber == StringNumber) maxString ="?";
            if(StringMaxNumber < StringNumber){
                StringMaxNumber = StringNumber;
                maxString = inputList.get(i);
            }
        }
        System.out.printf(maxString);
    }
}
