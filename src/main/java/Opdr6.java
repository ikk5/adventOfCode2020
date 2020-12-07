import org.apache.commons.lang3.StringUtils;
import utils.ReadFileUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Opdr6 extends AbstractOpdr {

    @Override
    public void opdrA() {
        List<String> ppl = ReadFileUtil.readStringFile("input6");
        Set<Character> groupAnswers = new HashSet<>();
        long sum = 0;
        for (int i = 0; i < ppl.size(); i++) {
            String s = ppl.get(i);
            if (i == ppl.size() - 1) {
                for (char answer : s.toCharArray()) {
                    groupAnswers.add(answer);
                }
                sum += groupAnswers.size();
            } else if (StringUtils.isBlank(s)) {
                sum += groupAnswers.size();
                groupAnswers.clear();
            } else {
                for (char answer : s.toCharArray()) {
                    groupAnswers.add(answer);
                }
            }
        }
        System.out.println("Sum: " + sum);
    }

    @Override
    public void opdrB() {
        List<String> ppl = ReadFileUtil.readStringFile("input6");

        List<Character> groupAnswers = new ArrayList<>();
        long sum = 0;
        boolean newGroup = true;
        for (int i = 0; i < ppl.size(); i++) {
            String s = ppl.get(i);
            if (i == ppl.size() - 1) {
                if (newGroup) {
                    sum += s.length();
                } else {
                    groupAnswers = mergeDupList(groupAnswers, s);
                    sum += groupAnswers.size();
                }
            } else if (StringUtils.isBlank(s)) {
                sum += groupAnswers.size();
                groupAnswers.clear();
                newGroup = true;
            } else if (!newGroup) {
                groupAnswers = mergeDupList(groupAnswers, s);
            } else {
                for (char answer : s.toCharArray()) {
                    groupAnswers.add(answer);
                }
                newGroup = false;
            }
        }
        System.out.println("Sum: " + sum);
    }

    private List<Character> mergeDupList(List<Character> groupAnswers, String s) {
        List<Character> dups = new ArrayList<>();
        List<Character> answers = new ArrayList<>();
        for (char c : s.toCharArray()) {
            answers.add(c);
        }
        for (char dup : groupAnswers) {
            if (answers.contains(dup)) {
                dups.add(dup);
            }
        }
        return dups;
    }
}
