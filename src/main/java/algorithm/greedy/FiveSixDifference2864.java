package algorithm.greedy;

import java.util.Scanner;

/*
   풀이 :
   이리저리 해보다가 못풀었다.
   다른사람의 풀이를 통해 문제 해결 방법을 알아보았다.
   문제의 포인트는 max는 5일경우 6으로 봐야하기때문에 자릿수*1 더하고
   min은 6일경우 5로 봐야하기 때문에 자릿수 * 1를 뺀다.

   다른사람 코드에서 배울점 :
   String 클래스에 replace 메소드를 통해 모든 "5" > "6"으로 바꿀수있었다...ㅠㅠ
   String a = "123555"
   a.replace("5","6")
   replace 와 replaceAll의 차이점은 정규식을 사용할수있냐의 차이이다.

   String str = "aaabbbccccabcddddabcdeeee";

  String result1 = str.replace("abc", "왕");
  String result2 = str.replaceAll("[abc]", "왕");

  System.out.println("replace result->"+ result1);
  System.out.println("replaceAll result->"+ result2);

 결과값

 replace result->aaabbbcccc왕dddd왕deeee
 replaceAll result->왕왕왕왕왕왕왕왕왕왕왕왕왕dddd왕왕왕deeee
 */
public class FiveSixDifference2864 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int max = a+b, min = a+b, digit = 1;
        int[] numberArr = new int[]{a,b};

        for(int i=0; i<numberArr.length; i++){
            while(numberArr[i] !=0){
                if(numberArr[i] % 10 == 5)
                    max += digit;
                if(numberArr[i] % 10 == 6)
                    min -= digit;
                numberArr[i] /= 10;
                digit *= 10;
            }
            digit = 1;
        }
        System.out.printf("%d %d",min, max);
    }
}
