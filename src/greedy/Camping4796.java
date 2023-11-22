package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Camping4796 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String[] caseArr = br.readLine().split("\\s");
        int start = 0;
        boolean test = false;
        ArrayList<String> CaseList = new ArrayList<>();

        for(int i=start; i<caseArr.length; i++){
            if (caseArr[i].equals("0")) {
                test = false;
                start += 3;
                break;
            }
            else
                test = true;
            if(i == caseArr.length -1)
                start += 3;
        }
        if(test) {
            for (int i = start - 3; i < caseArr.length; i++) {
                CaseList.add(caseArr[i]);
            }
        }
    }
}
