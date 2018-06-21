package codekata.stringcalculator.extractors;

public interface NumberExtractor {
    public boolean accept(String tallsekvens);

    public int[] extract(String tallsekvens);
}