package algorithm.programmers.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GetReportResult {
    public int[] solution(String[] idList, String[] reports, int limit) {
        Set<String> idSet = new HashSet<>();
        Map<String, Set<String>> reportMap = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();

        // 이름자 id 정리
        for (int i = 0; i < idList.length; i++) {
            String id = idList[i];
            idSet.add(id);
            reportMap.put(id, new HashSet<>());
            indexMap.put(id, i);
        }

        // 신고 하기
        for (int i = 0; i < reports.length; i++) {
            String[] reportArr = reports[i].split(" ");
            String person = reportArr[0];
            String target = reportArr[1];

            // 이름 명단에 없으면 패스
            if (!idSet.contains(person) || !idSet.contains(target)) continue;

            // 신고 안한 사람이면 추가
            Set<String> reporterSet = reportMap.get(target);
            reporterSet.add(person);
        }

        int[] mailArr = new int[idList.length];

        // 메일 전송
        for (int i = 0; i < idList.length; i++) {
            String id = idList[i];

            Set<String> reporterSet = reportMap.get(id);

            // 메일 전송 대상
            if (reporterSet.size() >= limit) {
                for (String reporter : reporterSet) {
                    int index = indexMap.get(reporter);
                    mailArr[index] += 1;
                }
            }
        }
        return mailArr;
    }
}
