package algorithm.programmers.enterprise.kakao;

public class PrimeCountFromKBase {
    static final int MAX_NUM = 1000000;

    public int solution(int num, int base) {
        String[] numbers = convertBaseFromNum(num, base).split("0");

        int count = 0;
        for(int index = 0; index < numbers.length; index++){
            if(isBlank(numbers, index)){
                continue;
            }

            if(isPrime(Long.parseLong(numbers[index]))){
                count++;
            }
        }

        return count;
    }

    static String convertBaseFromNum(int num, int base){
        StringBuilder sb = new StringBuilder();
        while(num >= base){
            int remain = num % base;
            sb.append(remain);
            num /= base;
        }
        sb.append(num);
        return sb.reverse().toString();
    }

    static boolean isPrime(Long num){
        if(num == 1L) return false;

        int repeat = (int) Math.sqrt(num);
        for(int i = 2; i <= repeat; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    static boolean isBlank(String[] arr, int index){
        return arr[index].equals("");
    }
}
