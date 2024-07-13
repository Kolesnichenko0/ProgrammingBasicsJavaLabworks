package part2.labwork6.second.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PrimeFactors {
    private int from;
    private int to;
    private final Map<Integer, List<Integer>> map = new LinkedHashMap<>();

    public PrimeFactors(int from, int to) {
        setRange(from, to);
    }

    private void setRange(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("Invalid input: 'From' must be less than or equal to 'To'.");
        }
        this.from = from;
        this.to = to;
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }

    public synchronized Map<Integer, List<Integer>> getMap() {
        return map;
    }

    public Integer getLastFound() {
        Integer lastKey = null;
        for (Integer key : getMap().keySet()) {
            lastKey = key;
        }
        return lastKey;
    }

    public String getFormattedPrimeFactors(Integer number) {
        if (!getMap().containsKey(number)) {
            return "Number " + number + " is not processed.";
        }
        List<Integer> factors = getMap().get(number);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Number: %d\t\t Prime factors: ", number));
        if (factors == null || factors.isEmpty()) {
            sb.append("None");
        } else {
            for (int i = 0; i < factors.size(); i++) {
                sb.append(factors.get(i));
                if (i < factors.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }
}
