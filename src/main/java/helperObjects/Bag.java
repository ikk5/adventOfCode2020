package helperObjects;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Bag {
    public Bag(String bagName) {
        this.bagName = bagName;
    }

    private String bagName;

    private Map<Bag, Integer> contents = new HashMap<>();

    public void addContent(int amount, Bag bag) {
        contents.put(bag, amount);
    }

    public boolean containsBag(String wantedBag) {
        return contents.entrySet().stream().anyMatch(bag -> StringUtils.equals(bag.getKey().getBagName(), wantedBag) || bag.getKey().containsBag(wantedBag));
    }

    public int countContent() {
        int counter = 0;
        for (Map.Entry<Bag, Integer> entry : contents.entrySet()) {
            counter += entry.getValue() + entry.getValue() * entry.getKey().countContent();
        }
        return counter;
    }
}
