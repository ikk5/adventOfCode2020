import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ReadFileUtil;

public class Opdr10 extends AbstractOpdr {
    @Override
    public void opdrA() {
        List<Integer> adapters = ReadFileUtil.readIntFile("input10");
        adapters.sort(Integer::compareTo);
        int jolt1Changes = changes(adapters, 1);
        int jolt3Changes = changes(adapters, 3) + 1;
        System.out.println(String.format("1-jolt changes (%d) * 3-jolt changes (%d) = %d", jolt1Changes, jolt3Changes, (jolt1Changes * jolt3Changes)));
    }

    public int changes(List<Integer> adapters, int joltChanges) {
        int changeCounter = 0;
        int joltage = 0;
        for (int index = 0; index < adapters.size(); index++) {
            if (adapters.get(index) - joltage == joltChanges) {
                changeCounter++;
            }
            joltage = adapters.get(index);
        }
        return changeCounter;
    }

    @Override
    public void opdrB() {
        List<Integer> adapters = ReadFileUtil.readIntFile("input10");
        adapters.add(0);
        adapters.sort(Integer::compareTo);
        Map<Integer, Long> gevondenPaden = new HashMap<>();
        long paths = poging3(adapters, 0, gevondenPaden);
        System.out.println("Aantal paden: " + paths);
    }

    public long poging3(List<Integer> input, int previousIndex, Map<Integer, Long> gevondenPaden) {
        if (previousIndex == input.size() - 1) {
            return 1;
        }
        if (gevondenPaden.get(previousIndex) != null) {
            return gevondenPaden.get(previousIndex);
        }
        long counter = 0;
        for (int index = previousIndex + 1; index < input.size(); index++) {
            if (input.get(index) - input.get(previousIndex) <= 3) {
                counter += poging3(input, index, gevondenPaden);
            }
        }
        gevondenPaden.put(previousIndex, counter);
        return counter;
    }
}
