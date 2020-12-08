import org.apache.commons.lang3.StringUtils;
import utils.ReadFileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Opdr8 extends AbstractOpdr {
    @Override
    public void opdrA() {
        List<String> instructions = ReadFileUtil.readStringFile("input8");
        List<Integer> runInstructions = new ArrayList<>();
        long accumulator = 0;
        String regex = "(\\S+) \\+?(-?[0-9]+)";
        for (int index = 0; index < instructions.size() && !runInstructions.contains(index); index++) {
            runInstructions.add(index);
            Matcher matcher = Pattern.compile(regex).matcher(instructions.get(index));
            if (matcher.matches()) {
                String operation = matcher.group(1);
                int nr = Integer.parseInt(matcher.group(2));
                if (StringUtils.equals("acc", operation)) {
                    accumulator += nr;
                } else if (StringUtils.equals("jmp", operation)) {
                    index += nr - 1;
                }
            }
        }
        System.out.println("Accumulator: " + accumulator);
    }

    @Override
    public void opdrB() {
        List<String> instructions = ReadFileUtil.readStringFile("input8");
        List<Integer> runInstructions = new ArrayList<>();
        List<Integer> triedSwaps = new ArrayList<>();
        long accumulator = 0;
        String regex = "(\\S+) \\+?(-?[0-9]+)";
        boolean swapped = false;
        for (int index = 0; index < instructions.size(); index++) {
            if (runInstructions.contains(index)) {
                // begin loop, dus reset
                index = 0;
                runInstructions.clear();
                accumulator = 0;
                swapped = false;
            }

            runInstructions.add(index);
            Matcher matcher = Pattern.compile(regex).matcher(instructions.get(index));
            if (matcher.matches()) {
                String operation = matcher.group(1);
                int nr = Integer.parseInt(matcher.group(2));
                if (StringUtils.equals("acc", operation)) {
                    accumulator += nr;
                } else if (StringUtils.equals("jmp", operation)) {
                    if (!swapped && !triedSwaps.contains(index)) {
                        triedSwaps.add(index);
                        swapped = true;
                    } else {
                        index += nr - 1;
                    }
                } else if (StringUtils.equals("nop", operation)) {
                    if (!swapped && !triedSwaps.contains(index)) {
                        triedSwaps.add(index);
                        index += nr - 1;
                        swapped = true;
                    }
                }
            }
        }
        System.out.println("Accumulator: " + accumulator);
    }
}
