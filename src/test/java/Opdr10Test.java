import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import utils.ReadFileUtil;

class Opdr10Test {

    @Test
    void smallBTest() {
        List<Integer> input = Arrays.asList(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4);
        input.sort(Integer::compareTo);
        Map<Integer, Long> gevondenPaden = new HashMap<>();
        long test = new Opdr10().poging3(input, 0, gevondenPaden);
        assertEquals(8, test);
    }

    @Test
    void bigBTest() {
        List<Integer> input =
                Arrays.asList(0, 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31, 32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 48, 49, 52);
        Map<Integer, Long> gevondenPaden = new HashMap<>();

        long test = new Opdr10().poging3(input, 0, gevondenPaden);
        assertEquals(19208, test);
    }

    @Test
    void opdr10BTest() {
        List<Integer> input = ReadFileUtil.readIntFile("input10");
        input.add(0);
        input.sort(Integer::compareTo);

        Map<Integer, Long> gevondenPaden = new HashMap<>();
        long paden = new Opdr10().poging3(input, 0, gevondenPaden);
        assertEquals(3947645370368L, paden);
    }
}
