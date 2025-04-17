package algorithm.programmers.enterprise.kakao;

public class SongJustNow {
    public String solution(String m, String[] musicInfos) {
        String answer = "(None)";
        int maxTime = -1;

        m = m.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a")
                .replace("B#", "b");

        for(String music : musicInfos){
            String[] info = music.split(",");
            int play = getLastingTime(info[0], info[1]);

            info[3] = info[3].replace("C#", "c")
                    .replace("D#", "d")
                    .replace("F#", "f")
                    .replace("G#", "g")
                    .replace("A#", "a")
                    .replace("B#", "b");

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < play; i++){
                sb.append(info[3].charAt(i % info[3].length()));
            }

            if(sb.toString().contains(m)){
                if(maxTime < play){
                    maxTime = play;
                    answer = info[2];
                }
            }
        }
        return answer;
    }

    private static int getLastingTime(String s1, String s2){
        String[] t1 = s1.split(":");
        String[] t2 = s2.split(":");

        return (Integer.parseInt(t2[0]) * 60 + Integer.parseInt(t2[1]))
                - (Integer.parseInt(t1[0]) * 60 + Integer.parseInt(t1[1]));
    }
}
