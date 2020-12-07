import helperObjects.Bag;
import utils.ReadFileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Opdr7 extends AbstractOpdr {

    @Override
    public void opdrA() {
        List<String> bagLines = ReadFileUtil.readStringFile("input7");
        Map<String, Bag> bags = buildBagMap(bagLines);

        long goldCounter = bags.entrySet().stream().filter(bag -> bag.getValue().containsBag("shiny gold")).count();
        System.out.println("Bags containing shiny gold bags: " + goldCounter);
    }

    @Override
    public void opdrB() {
        List<String> bagLines = ReadFileUtil.readStringFile("input7");
        Map<String, Bag> bags = buildBagMap(bagLines);

        int contents = bags.get("shiny gold").countContent();
        System.out.println("Number of bags inside shiny gold: " + contents);
    }

    private Map<String, Bag> buildBagMap(List<String> bagLines) {
        Map<String, Bag> bags = new HashMap<>();
        // Maak eerst alle Bags
        String bagRegex = "(.*) bags contain (.*)\\.";
        for (String bagLine : bagLines) {
            Matcher matcher = Pattern.compile(bagRegex).matcher(bagLine);
            if (matcher.matches()) {
                String bagName = matcher.group(1);
                Bag bag = new Bag(bagName);
                bags.put(bag.getBagName(), bag);
            }
        }

        // Voeg de inhoud toe.
        String bagContentRegex = "(\\d+) (.*) bags?";
        for (String bagLine : bagLines) {
            Matcher matcher = Pattern.compile(bagRegex).matcher(bagLine);
            if (matcher.matches()) {
                Bag bag = bags.get(matcher.group(1));
                String contents = matcher.group(2);
                for (String bagContent : contents.split(", ")) {
                    Matcher contentMatcher = Pattern.compile(bagContentRegex).matcher(bagContent);
                    if (contentMatcher.matches()) {
                        String bagName = contentMatcher.group(2);
                        bag.addContent(Integer.parseInt(contentMatcher.group(1)), bags.get(bagName));
                    }
                }
            }
        }
        return bags;
    }
}
