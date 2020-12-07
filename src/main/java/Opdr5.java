import utils.ReadFileUtil;

import java.util.ArrayList;
import java.util.List;

public class Opdr5 extends AbstractOpdr {

    public void opdrA() {
        List<String> seating = ReadFileUtil.readStringFile("input5");

        int highestSeat = 0;
        for (String seat : seating) {
            String row = seat.substring(0, 7);
            row = row.replaceAll("B", "1").replaceAll("F", "0");
            int rowNr = Integer.parseInt(row, 2);
            String column = seat.substring(7);
            column = column.replaceAll("R", "1").replaceAll("L", "0");
            int columnNr = Integer.parseInt(column, 2);

            int seatNr = rowNr * 8 + columnNr;
            if (seatNr > highestSeat) {
                highestSeat = seatNr;
            }
        }
        System.out.println("Highest seat nr: " + highestSeat);
    }

    public void opdrB() {
        List<String> seating = ReadFileUtil.readStringFile("input5");

        List<Integer> seatNrs = new ArrayList<>();
        for (String seat : seating) {
            String row = seat.substring(0, 7);
            row = row.replaceAll("B", "1").replaceAll("F", "0");
            int rowNr = Integer.parseInt(row, 2);
            String column = seat.substring(7);
            column = column.replaceAll("R", "1").replaceAll("L", "0");
            int columnNr = Integer.parseInt(column, 2);

            int seatNr = rowNr * 8 + columnNr;
            seatNrs.add(seatNr);
        }
        seatNrs.sort(Integer::compareTo);
        int missingNo = 0;
        int previous = 0;
        for (int seatNr : seatNrs) {
            if (seatNr > 80 && seatNr - previous != 1) {
                missingNo = seatNr - 1;
            }
            previous = seatNr;
        }
        System.out.println("My seat: " + missingNo);
    }
}
