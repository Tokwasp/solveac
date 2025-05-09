package algorithm.programmers.enterprise.kakao;

public class Delivery {
    public long solution(int capacity, int homeCount,
                         int[] deliveries, int[] pickups) {

        int deliveryRemain = 0;
        int pickUpRemain = 0;
        long totalDist = 0;

        for(int i = homeCount - 1; i >= 0; i--){
            int repeatCount = 0;

            if(deliveryRemain < deliveries[i] || pickUpRemain < pickups[i]){
                while(deliveryRemain < deliveries[i] ||
                        pickUpRemain < pickups[i]){
                    repeatCount++;

                    deliveryRemain += capacity;
                    pickUpRemain += capacity;
                }
            }

            deliveryRemain -= deliveries[i];
            pickUpRemain -= pickups[i];
            totalDist += (i + 1) * repeatCount * 2;
        }

        return totalDist;
    }
}
