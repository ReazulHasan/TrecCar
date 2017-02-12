package edu.unh.cs.ir.eval;


/**
 * Object for passing parsed information to the performance evaluation classes
 */
public class RelevancyResult {

    private String query;
    private int[] results;
    private int totalRelevantDocuments;

    /**
     * Constructor to create object of the RelevancyResult class to pass data around
     * @param query
     * @param results
     * @param totalRelevantDocuments
     */
    public RelevancyResult(String query, int[] results, int totalRelevantDocuments) {
        this.query = query;
        this.results = results;
        this.totalRelevantDocuments=totalRelevantDocuments;
    }

    public String getQuery() {
        return query;
    }

    public int[] getResults() {
        return results;
    }

    public int getTotalRelevantDocuments() {
        return totalRelevantDocuments;
    }
}
