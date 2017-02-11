package edu.unh.cs.ir.eval;


/**
 * Object for passing parsed information to the performance evaluation classes
 */
public class RelevancyResult {


    private String query;
    private int[] relevancyResults;
    private int totalRelevantDocuments;




    public RelevancyResult(String query, int[] relevancyResults) {
        this.query = query;

        this.relevancyResults = relevancyResults;
    }

    public RelevancyResult(String query, int[] relevancyResults, int totalRelevantDocuments) {
        this.query = query;
        this.relevancyResults = relevancyResults;
        this.totalRelevantDocuments=totalRelevantDocuments;
    }

    public String getQuery() {
        return query;
    }

    public int[] getRelevancyResults() {
        return relevancyResults;
    }

    public int getTotalRelevantDocuments() {
        return totalRelevantDocuments;
    }
}
