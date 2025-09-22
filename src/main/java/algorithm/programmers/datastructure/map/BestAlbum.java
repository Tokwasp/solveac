package algorithm.programmers.datastructure.map;

import java.util.*;

public class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Play>> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int playCount = plays[i];

            if (map.get(genre) == null) {
                map.put(genre, new ArrayList<>());
            }

            Play play = new Play(i, playCount);
            List<Play> playList = map.get(genre);
            playList.add(play);
        }

        List<Integer> result = new ArrayList<>();

        while (map.size() != 0) {
            String maxGenre = "";
            int maxPlay = 0;

            // 가장 많이 재생된 장르 찾기
            for (Map.Entry<String, List<Play>> entry : map.entrySet()) {
                String genre = entry.getKey();
                List<Play> playList = entry.getValue();
                int totalPlay = playList.stream().mapToInt(a -> a.score).sum();

                if (totalPlay > maxPlay) {
                    maxPlay = totalPlay;
                    maxGenre = genre;
                }
            }

            // 많이 재생된 노래
            List<Play> playList = map.get(maxGenre);
            playList.sort(Comparator.naturalOrder());

            // 곡 한개이거나 두개 인 경우
            if (playList.size() == 1) {
                result.add(playList.get(0).number);
            } else {
                result.add(playList.get(0).number);
                result.add(playList.get(1).number);
            }

            // 장르 제거
            map.remove(maxGenre);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    static class Play implements Comparable<Play> {
        private int number;
        private int score;

        public Play(int number, int score) {
            this.number = number;
            this.score = score;
        }

        public int compareTo(Play another) {
            int result = another.score - this.score;
            if (result != 0) return result;
            return this.number - another.number;
        }
    }
}
