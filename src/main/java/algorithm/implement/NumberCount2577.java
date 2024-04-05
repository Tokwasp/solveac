package algorithm.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
  내 해결 방안 : A * B * C의 결과값을 받아 String으로 변환하여 split하여 각문자로 나눠서
                for문을 통해 검사한다.

  몰랐던것 : String.valueOf

  다른사람의 답안에서 배울것 :  char - '0'

  char는 1byte의 크기의 정수이긴하지만 데이터가 들어오면 문자를 출력합니다.
  char - '0'을 할경우 더큰 타입으로 형변환이 이루어져 int - int 가 됩니다.
  String str = "1" str.charat(0)을 하면 문자1이 나옵니다
  '1'을 int형으로 변환하면 아스키코드 49가 나오게 되는데 저희는 int 1을 얻어야 하므로 48을 뺴야합니다.
  그레서 아스키코드 48인 '0'를 빼는것입니다.

  풀이2)
  끝자리를 얻으면 되므로 결과값 %10으로 끝자리를 얻고 /10하여 자릿수를 제거한다.
  결과값이 0이 될때까지 반복한다.

 */
public class NumberCount2577 {
    public static void main(String[] args) throws IOException {
        int[] ABCArr = new int[3];
        int product = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<3; i++) {
            ABCArr[i] = Integer.parseInt(br.readLine());
            product *= ABCArr[i];
        }
        String[] productStringArr = String.valueOf(product).split("");
        String[] NumberStringArr =new String[]{"0","1","2","3","4","5","6","7","8","9"};
        int[] resultArr= new int[10];

        for(int i=0; i<productStringArr.length; i++){
            for(int j=0; j<10; j++){
                if(productStringArr[i].equals(NumberStringArr[j]))
                    resultArr[j] += 1;
            }
        }
        for(int i=0; i<resultArr.length; i++){
            System.out.println(resultArr[i]);
        }
    }
}
