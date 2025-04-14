package algorithm.programmers.enterprise.kakao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenChattingRoom {
    public static String[] solution(String[] records) {
        Map<String, String> nameGroupId = new HashMap<>();

        for (int i = 0; i < records.length; i++) {
            String[] recordArr = records[i].split(" ");

            if(recordArr[0].equals("Enter") || recordArr[0].equals("Change")){
                nameGroupId.put(recordArr[1], recordArr[2]);
            }
        }

        List<String> logs = new ArrayList<>();

        for (int i = 0; i < records.length; i++) {
            String[] recordArr = records[i].split(" ");
            String action = recordArr[0];

            if(action.equals("Enter")){
                logs.add(nameGroupId.get(recordArr[1]) + "님이 들어왔습니다.");
            }

            if(action.equals("Leave")){
                logs.add(nameGroupId.get(recordArr[1]) + "님이 나갔습니다.");
            }
        }

        String[] results = new String[logs.size()];
        for (int i = 0; i < logs.size(); i++) {
            results[i] = logs.get(i);
        }
        return results;
    }
}
