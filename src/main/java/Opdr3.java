import helperObjects.Coord;
import utils.ReadFileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Opdr3 extends AbstractOpdr {

    public void opdrA() {
        List<String> kaartje = ReadFileUtil.readStringFile("input3");
        int rowLength = kaartje.get(0).length();
        int bottomRow = kaartje.size();
        Map<Coord, Character> grid = new HashMap<>();
        for (int rowNr = 0; rowNr < kaartje.size(); rowNr++) {
            String row = kaartje.get(rowNr);
            char[] charArray = row.toCharArray();
            for (int columnNr = 0; columnNr < charArray.length; columnNr++) {
                char point = charArray[columnNr];
                grid.put(new Coord(rowNr, columnNr), point);
            }
        }

        long bomenCounter = countBomen(rowLength, bottomRow, grid, 3, 1);

        System.out.println("Aantal bomen: " + bomenCounter);
    }

    public void opdrB() {
        List<String> kaartje = ReadFileUtil.readStringFile("input3");
        int rowLength = kaartje.get(0).length();
        int bottomRow = kaartje.size();
        Map<Coord, Character> grid = new HashMap<>();
        for (int rowNr = 0; rowNr < kaartje.size(); rowNr++) {
            String row = kaartje.get(rowNr);
            char[] charArray = row.toCharArray();
            for (int columnNr = 0; columnNr < charArray.length; columnNr++) {
                char point = charArray[columnNr];
                grid.put(new Coord(rowNr, columnNr), point);
            }
        }

        long bomenCounter = countBomen(rowLength, bottomRow, grid, 1, 1);
        bomenCounter *= countBomen(rowLength, bottomRow, grid, 3, 1);
        bomenCounter *= countBomen(rowLength, bottomRow, grid, 5, 1);
        bomenCounter *= countBomen(rowLength, bottomRow, grid, 7, 1);
        bomenCounter *= countBomen(rowLength, bottomRow, grid, 1, 2);

        System.out.println("Aantal bomen: " + bomenCounter);
    }

    private int countBomen(int rowLength, int bottomRow, Map<Coord, Character> grid, int right, int down) {
        int bomenCounter = 0;
        int row = 0;
        int column = 0;
        while (row < bottomRow) {
            if (column >= rowLength) {
                column -= rowLength;
            }
            if (grid.get(new Coord(row, column)) == '#') {
                bomenCounter++;
            }
            row += down;
            column += right;
        }
        return bomenCounter;
    }
}
