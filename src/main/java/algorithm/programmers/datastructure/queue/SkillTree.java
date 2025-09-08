package algorithm.programmers.datastructure.queue;

import java.util.*;

public class SkillTree {
    public int solution(String skill, String[] skillTrees) {
        List<Character> skills = new ArrayList<>();
        Set<Character> skillSet = new HashSet<>();

        for (int i = 0; i < skill.length(); i++) {
            char skillChar = skill.charAt(i);
            skills.add(skillChar);
            skillSet.add(skillChar);
        }

        int count = 0;
        for (int i = 0; i < skillTrees.length; i++) {
            String skillTree = skillTrees[i];
            Queue<Character> queue = new LinkedList<>(skills);

            boolean isSafeSkillTree = true;
            for (int index = 0; index < skillTree.length(); index++) {
                char skillChar = skillTree.charAt(index);

                if (queue.isEmpty()) {
                    break;
                }

                if (skillSet.contains(skillChar) && skillChar != queue.peek()) {
                    isSafeSkillTree = false;
                    break;
                }

                if (skillChar == queue.peek() && skillSet.contains(skillChar)) {
                    queue.poll();
                }
            }

            if (isSafeSkillTree) {
                count++;
            }
        }
        return count;
    }
}
