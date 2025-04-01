package algorithm.programmers.implement;

import java.util.ArrayList;
import java.util.List;

class OneCache {
    public int solution(int cacheSize, String[] cities) {
        List<String> caches = new ArrayList<>();

        if(cacheSize == 0){
            return cities.length * 5;
        }

        int score = 0;
        for(int index = 0; index < cities.length; index++){
            String currentCity = cities[index].toUpperCase();
            boolean isCacheFind = false;

            for(int cacheIndex = 0; cacheIndex < caches.size(); cacheIndex++){
                if(caches.contains(currentCity)){
                    isCacheFind = true;
                    caches.remove(currentCity);
                    break;
                }
            }

            if(caches.size() == cacheSize){
                caches.remove(0);
            }
            caches.add(currentCity);

            if(isCacheFind){
                score += 1;
            }
            else{
                score += 5;
            }
        }
        return score;
    }
}
