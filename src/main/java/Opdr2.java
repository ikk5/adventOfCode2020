import utils.ReadFileUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Opdr2 extends AbstractOpdr {

    public void opdrA() {
        List<String> input = ReadFileUtil.readStringFile("input2");
        int counter = 0;
        for (String line : input) {
            String[] parts = line.split(" ");
            int min = parseInt(parts[0].split("-")[0]);
            int max = parseInt(parts[0].split("-")[1]);
            String letter = parts[1].replaceAll(":", "");
            String password = parts[2];

            int occurrences = password.replaceAll("[^" + letter + "]", "").length();

            if (occurrences >= min && occurrences <= max) {
                counter++;
            }
        }
        System.out.println("Valid: " + counter);
    }

    public void opdrB() {
        List<String> input = ReadFileUtil.readStringFile("input2");
        String groupPolicyRegex = "(\\d*)-(\\d*) ([a-z]): ([a-z]*)";
        int counter = 0;
        for (String line : input) {
            Matcher matcher = Pattern.compile(groupPolicyRegex).matcher(line);
            if (!matcher.matches()) {
                System.out.println("no match: " + line);
                continue;
            }
            int min = parseInt(matcher.group(1));
            int max = parseInt(matcher.group(2));
            char letter = matcher.group(3).charAt(0);
            String password = matcher.group(4);

            boolean minEquals = false;
            boolean maxEquals = false;
            if (password.length() >= min) {
                minEquals = password.charAt(min - 1) == letter;
            }
            if (password.length() >= max) {
                maxEquals = password.charAt(max - 1) == letter;
            }

            if (!(maxEquals && minEquals) && (maxEquals || minEquals)) {
                counter++;
            }
        }
        System.out.println("Valid: " + counter);
    }
}
