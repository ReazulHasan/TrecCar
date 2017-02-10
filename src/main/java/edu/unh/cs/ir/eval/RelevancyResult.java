package edu.unh.cs.ir.eval;


/**
 * Created by matt on 2/10/17.
 */
public class RelevancyResult {


    private String query;
    private int[] relevancyResults;


    public RelevancyResult(String query, int[] relevancyResults) {
        this.query = query;
        this.relevancyResults = relevancyResults;
    }

    public String getQuery() {
        return query;
    }

    public int[] getRelevancyResults() {
        return relevancyResults;
    }
}
