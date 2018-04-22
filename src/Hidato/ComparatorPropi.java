package Hidato;

import javafx.util.Pair;
import java.util.Comparator;

public class ComparatorPropi implements Comparator<Pair<Pair<Integer, Integer>, Integer>>{
    @Override
    public int compare(Pair<Pair<Integer, Integer>, Integer> o1, Pair<Pair<Integer, Integer>, Integer> o2) {
        if (o1.getValue() > o2.getValue()) return 1;
        if (o1.getValue() < o2.getValue()) return -1;
        return 0;
    }
}
