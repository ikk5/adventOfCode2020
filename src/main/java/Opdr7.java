import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helperObjects.Bag;
import utils.ReadFileUtil;

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
        String bagRegex = "(.*) bags contain (.*)\\.";
        String bagContentRegex = "(\\d+) (.*) bags?";

        for (String bagLine : bagLines) {
            Matcher matcher = Pattern.compile(bagRegex).matcher(bagLine);
            if (matcher.matches()) {
                String bagName = matcher.group(1);
                Bag bag = getOrAddBag(bags, bagName);
                addInnerBags(bags, bagContentRegex, bag, matcher.group(2));
            }
        }
        return bags;
    }

    private void addInnerBags(Map<String, Bag> bags, String bagContentRegex, Bag bag, String contents) {
        for (String bagContent : contents.split(", ")) {
            Matcher contentMatcher = Pattern.compile(bagContentRegex).matcher(bagContent);
            if (contentMatcher.matches()) {
                String innerBagName = contentMatcher.group(2);

                Bag innerBag = getOrAddBag(bags, innerBagName);
                bag.addContent(Integer.parseInt(contentMatcher.group(1)), innerBag);
            }
        }
    }

    private Bag getOrAddBag(Map<String, Bag> bags, String bagName) {
        Bag bag = bags.get(bagName);
        if (bag == null) {
            bag = new Bag(bagName);
            bags.put(bagName, bag);
        }
        return bag;
    }
}
