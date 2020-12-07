import org.apache.commons.lang3.StringUtils;
import utils.ReadFileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Opdr4 extends AbstractOpdr {

    public void opdrA() {
        List<String> input = ReadFileUtil.readStringFile("input4");
        List<String> passports = new ArrayList<>();
        StringBuilder passport = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (i == input.size() - 1) {
                passport.append(" ").append(line);
                passports.add(passport.toString());
            } else if (StringUtils.isBlank(line)) {
                passports.add(passport.toString());
                passport = new StringBuilder();
            } else {
                passport.append(" ").append(line);
            }
        }

        long counter = 0;

        for (String pass : passports) {
            if (pass.contains("byr:") && pass.contains("iyr:") && pass.contains("eyr:") && pass.contains("hgt:") &&
                    pass.contains("hcl:") && pass.contains("ecl:") && pass.contains("pid:")) {
                counter++;
            }
        }
        System.out.println("Valid: " + counter);
    }

    public void opdrB() {
        List<String> input = ReadFileUtil.readStringFile("input4");
        StringBuilder passport = new StringBuilder();
        List<HashMap<String, String>> passportKeyValues = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (i == input.size() - 1) {
                passport.append(" ").append(line);
                passportKeyValues.add(mapPaswordValues(passport.toString()));
            } else if (StringUtils.isBlank(line)) {
                passportKeyValues.add(mapPaswordValues(passport.toString()));
                passport = new StringBuilder();
            } else {
                passport.append(" ").append(line);
            }
        }

        String byrRegex = "(19[2-9][0-9]|200[0-2])";
        String iyrRegex = "(201[0-9]|2020)";
        String eyrRegex = "(202[0-9]|2030)";
        String hgtRegex = "(1[5-8][0-9]cm|19[0-3]cm|59in|6[0-9]in|7[0-6]in)";
        String hclRegex = "#([0-9]|[a-f]){6}";
        String eclRegex = "amb|blu|brn|gry|grn|hzl|oth";
        String pidRegex = "[0-9]{9}";

        long counter = 0;
        for (HashMap<String, String> pass : passportKeyValues) {
            try {
                if (pass.get("byr").matches(byrRegex) && pass.get("iyr").matches(iyrRegex) && pass.get("eyr").matches(eyrRegex) &&
                        pass.get("hgt").matches(hgtRegex) && pass.get("hcl").matches(hclRegex) && pass.get("ecl").matches(eclRegex)
                        && pass.get("pid").matches(pidRegex)) {
                    counter++;
                }
            } catch (NullPointerException e) {
                // Er ontbreekt een waarde, dus gewoon door met de volgende
            }
        }

        System.out.println("Valid: " + counter);
    }

    private HashMap<String, String> mapPaswordValues(String passport) {
        HashMap<String, String> keyValue = new HashMap<>();
        for (String part : passport.split(" ")) {
            if (StringUtils.isNotBlank(part)) {
                keyValue.put(part.split(":")[0], part.split(":")[1]);
            }
        }
        return keyValue;
    }
}
