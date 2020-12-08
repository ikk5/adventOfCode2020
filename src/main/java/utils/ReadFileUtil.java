package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileUtil {

    public static List<Integer> readIntFile(String filename) {
        List<Integer> input = new ArrayList<>();
        try {
            File myObj = new File("D:\\adventOfCode2020\\src\\main\\resources\\" + filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(Integer.valueOf(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return input;
    }

    public static List<String> readStringFile(String filename) {
        List<String> input = new ArrayList<>();
        try {
            File myObj = new File("D:\\adventOfCode2020\\src\\main\\resources\\" + filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return input;
    }
}
