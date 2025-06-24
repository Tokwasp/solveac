package algorithm.programmers.datastructure.set;

import java.util.HashSet;
import java.util.Set;

public class BlackListUser {
    private static final char WILD_CARD = '*';

    private static String[] userIds;
    private static String[] bannedIds;
    private static Set<Set<String>> resultSet;
    private static Set<String> bannedIdSet;

    public int solution(String[] userIds, String[] bannedIds) {
        this.userIds = userIds;
        this.bannedIds = bannedIds;
        resultSet = new HashSet<>();
        bannedIdSet = new HashSet();

        dfs(0);
        return resultSet.size();
    }

    private static void dfs(int depth){
        if(depth == bannedIds.length){
            resultSet.add(new HashSet<>(bannedIdSet));
            return;
        }

        for(int index = 0; index < userIds.length; index++){
            if(bannedIdSet.contains(userIds[index])){
                continue;
            }

            if(isMatchBannedId(bannedIds[depth], userIds[index])){
                bannedIdSet.add(userIds[index]);
                dfs(depth + 1);
                bannedIdSet.remove(userIds[index]);
            }
        }
    }

    private static boolean isMatchBannedId(String bannedId, String userId){
        if(isNotMatchLength(bannedId, userId)){
            return false;
        }

        for(int index = 0; index < userId.length(); index++){
            char bannedIdChar = bannedId.charAt(index);
            char userIdChar = userId.charAt(index);

            if(isNotWildCard(bannedIdChar) && isNotMatchChar(bannedIdChar, userIdChar)){
                return false;
            }
        }
        return true;
    }

    private static boolean isNotMatchLength(String string, String compareString){
        return string.length() != compareString.length();
    }

    private static boolean isNotWildCard(char ch){
        return ch != WILD_CARD;
    }

    private static boolean isNotMatchChar(char ch, char compareCh){
        return ch != compareCh;
    }
}
