package codekata.stringcalculator.extractors;

import static java.util.Arrays.stream;

public class NoConfigExtractor implements NumberExtractor {

    @Override
    public boolean accept(String tallsekvens) {
	return !tallsekvens.startsWith("//");
    }

    @Override
    public int[] extract(String tallsekvens) {
	String delimiter = "[\n,]";
	return stream(tallsekvens.split(delimiter)).mapToInt(Integer::valueOf).toArray();
    }

}
