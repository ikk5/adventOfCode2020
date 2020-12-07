import utils.ReadFileUtil;

import java.util.List;

public class Opdr1 extends AbstractOpdr {

    public void opdrA() {
        List<Integer> input = ReadFileUtil.readIntFile("input1");
        for (int index = 0; index < input.size(); index++) {
            int nr1 = input.get(index);

            for (int nr2 : input.subList(index, input.size())) {
                if (nr1 + nr2 == 2020) {
                    System.out.println(nr1 * nr2);
                }
            }
        }
    }

    public void opdrB() {
        List<Integer> input = ReadFileUtil.readIntFile("input1");
        for (int index = 0; index < input.size(); index++) {
            int nr1 = input.get(index);
            List<Integer> subList = input.subList(index, input.size());
            for (int innerIndex = 0; innerIndex < subList.size(); innerIndex++) {
                int nr2 = subList.get(innerIndex);

                for (int nr3 : input.subList(innerIndex, input.size())) {
                    if (nr1 + nr2 + nr3 == 2020) {
                        System.out.println(nr1 * nr2 * nr3);
                    }
                }
            }
        }
    }
}
