package algorithm.baekjoon.implement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 문제를보고 i<10, i<100, i<1000, i<10000일때로 나눌려고 했는데
 좀더 생각해보니 각 자리수로 나눈 몫을 더하면 되니까 00000 이라 생각하고 
 한 케이스로 하면 되겠다. 숫자가 겹치면 안되므로 HashSet을 사용하자.
 */
public class SelfNumber4673 {

    public static void main(String[] args) throws IOException {
        List<Integer> selfNumber = selfNumber();
        Object[] result = selfNumber.toArray();
        for(int i=0; i<result.length; i++) {
            System.out.println(result[i]);
        }
    }

    static List<Integer> selfNumber() {
        int result = 0;
        int Myself = 0;
        int quotient = 0;
        int tensQuotient = 0;
        int hundredsQuotient = 0;
        int thousandQuotient = 0;

        HashSet<Integer> notSelfNumber = new HashSet<>();
        List<Integer> selfNumber = new ArrayList<>();

        //셀프 넘버가 아닌경우를 찾는다.
        for(int i=1; i<=10000; i++) {
            thousandQuotient = i / 1000;
            //백의 자리 몫은 천의자리 경우 8877 처럼 8877/100 = 88이 된다.
            //필요한것은 백의자리8이므로 천의자리를 제거한다.
            hundredsQuotient = i / 100 - i/1000 * 10;
            //십의 자리 몫은 백의자리 경우 887 처럼 887/10 = 88이되는데
            //필요한것은 십의자리 8이므로 백의자리윗자리를 제거한다.
            tensQuotient = i /10 - i/100 * 10;
            quotient = i % 10;
            Myself = i;
            result = thousandQuotient + hundredsQuotient + tensQuotient + quotient + Myself;
            notSelfNumber.add(result);
        }

        //셀프넘버 찾기
        for(int i=1; i<=10000; i++) {
            if(!notSelfNumber.contains(i)) {
                selfNumber.add(i);
            }
        }

        return selfNumber;
    }
}