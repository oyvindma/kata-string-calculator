package codekata.stringcalculator.extractors;

public class NillExtractor implements NumberExtractor {

    @Override
    public boolean accept(String tallsekvens) {
	return tallsekvens == null || tallsekvens.trim().equals("");
    }

    @Override
    public int[] extract(String tallsekvens) {
	return new int[] { 0 };
    }

}