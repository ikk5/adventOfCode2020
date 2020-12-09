import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import utils.ReadFileUtil;

public class Opdr9 extends AbstractOpdr {
    @Override
    public void opdrA() {
        List<Long> input = ReadFileUtil.readLongFile("input9");
        long nr = findFailure(input, 25);
        System.out.println("First failed nr: " + nr);
    }

    private long findFailure(List<Long> input, int preambleLength) {
        LinkedList<Long> preamble = new LinkedList<>();
        for (long nr : input) {
            if (preamble.size() == preambleLength) {
                if (!canSumToNr(preamble, nr)) {
                    return nr;
                }
                preamble.removeFirst();
            }
            preamble.addLast(nr);
        }
        return 0;
    }

    @Override
    public void opdrB() {
        List<Long> input = ReadFileUtil.readLongFile("input9");
        long failure = findFailure(input, 25);
        List<Long> nrsAddingToFailure = sumToFailure(input, failure);
        nrsAddingToFailure.sort(Long::compareTo);
        long sum = nrsAddingToFailure.get(0) + nrsAddingToFailure.get(nrsAddingToFailure.size() - 1);
        System.out.println("Encryption weakness: " + sum);

    }

    private List<Long> sumToFailure(List<Long> input, long failure) {
        Integer startIndex = null;
        long sum = 0;
        for (int index = 0; index < input.size(); index++) {
            if (startIndex == null) {
                startIndex = index;
            }
            sum += input.get(index);
            if (sum == failure) {
                return input.subList(startIndex, index + 1);
            } else if (sum > failure) {
                index = startIndex;
                startIndex = null;
                sum = 0;
            }
        }
        return new ArrayList<>();
    }

    private boolean canSumToNr(LinkedList<Long> preamble, long wanted) {
        for (int index = 0; index < preamble.size(); index++) {
            long nr1 = preamble.get(index);

            for (long nr2 : preamble.subList(index, preamble.size())) {
                if (nr1 + nr2 == wanted) {
                    return true;
                }
            }
        }
        return false;
    }
}
