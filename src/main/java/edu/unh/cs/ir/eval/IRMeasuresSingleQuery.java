package edu.unh.cs.ir.eval;

import java.util.ArrayList;

/**
 * This class calculates basic information retrieval measures for a single query.
 */
public class IRMeasuresSingleQuery {

    private ArrayList<Relevancy> results;
    private int[] intResults;
    private int totalRelevant=0;
    private String query;

    /**
     * Constructor takes an int array of 0s and 1s for ordered document relevance. The input
     * is an int array of 0 or 1 where 0 is not-relevant and 1 is relevant.  The int at index 0
     * is the highest ranked retrieved object (such as a document).  The next indexed object is
     * the next most relevant document.
     * @param results
     * example {1,0,1,0} where the first and third documents are relevant and the second
     * and fourth documents are not relevant.
     */
    public IRMeasuresSingleQuery(int [] results) {

        loadResults(results);
    }

    /**
     *Constructor allows for indicating total number of relevant documents in the corpus.
     * This allows for calculating measures, such as recall, where the number of relevant
     * documents exceeds the number of documents in the result set.  For example, this
     * constructor could be useful if the result
     * set was size 10 and the corpus has 50 total relevant documents.
     * @param results For
     * example {1,0,1,0} where the first and third documents are relevant and the second
     * and fourth documents are not relevant.
     * @param totalRelevant Total number of relevant documents in the corpus
     */
    public IRMeasuresSingleQuery(int [] results, int totalRelevant) {

        loadResults(results);

        //Overwrites relevant from results set
        this.totalRelevant=totalRelevant;
    }

    /**
     * Constructor takes a {@link RelevancyResult} object
     * @param relevancyResult RelevancyResult object
     */
    public IRMeasuresSingleQuery(RelevancyResult relevancyResult){
        loadResults(relevancyResult.getResults());
        this.totalRelevant=relevancyResult.getTotalRelevantDocuments();
        this.query=relevancyResult.getQuery();
    }


    /**
     * Returns the query for this metrics analysis
     */
    public String getQuery() {
        return query;
    }

    /**
     * Returns the total number of relevant documents
     * @return  Count of either the number of relevant documents in the result set
     * or the number of total relevant documents in the corpus (if specified in the
     * constructor).
     */
    public int getTotalRelevantCount() {
        return totalRelevant;
    }

    /**
     * Returns the result set passed to the constructor as an
     * integer array
     *
     */
    public int[] getIntResults(){return intResults;}

    /**
     * Returns the result set passed to the constructor as an
     * ArrayList of Relevancy enums.
     *
     */
    public ArrayList<Relevancy> getResults() {
        return results;
    }

    /**
     * Returns the size of the result passed to the constructor.
     * For example if there are 10 relevancy results, it returns 10.
     *
     */
    public int getResultsSize(){
        return results.size();
    }

    /**
     * Returns precision for entire result set
     *
     */
    public double getPrecision(){

        int accum = getRelevantResultsRetrievedCount();
        return (double)accum/ getResultsSize();
    }

    /**
     *  Returns precision for precision@k
     *  @param k the cut-off size in the results set
     *  @throws IllegalArgumentException if k is less than 1
     */
    public double getPrecision(int k){

        if(k<1){
            IllegalArgumentException e = new IllegalArgumentException("K can't be less than 1");
            throw e;
        }

        double accum = (double)getRelevantResultsRetrievedCount(Math.min(k, getResultsSize()));

        //Adjust denominator for relevant documents that were not retrieved
        int denom=k;
        if (k>getResultsSize()){
            int nonRetrieved = Math.max(getTotalRelevantCount(),k)-getRelevantResultsRetrievedCount();
            int diff = Math.min(k-getResultsSize(),nonRetrieved);
            denom=getResultsSize()+diff;
        }

        return accum/ denom;
    }

    /**
     *  Returns recall
     */
    public double getRecall(){
        return (double) getRelevantResultsRetrievedCount()/getTotalRelevantCount();
    }


    /**
     *  Returns balanced f score for the result set
     */
    public double getBalancedF1Score(){
        double precision = getPrecision();
        double recall = getRecall();

        return 2 * (precision*recall)/(precision+recall);
    }

    /**
     * Returns the R-Precision
     */
    public double getRPrecision(){
        return (double) getRelevantResultsRetrievedCount(Math.min(getResultsSize(),getTotalRelevantCount()))/getTotalRelevantCount();
    }


    /**
     *
     * Returns the average precsion of the result set
     */
    public double getAveragePrecision(){
        double accum =0;

        for(int i=0;i< getResultsSize();i++){
            if(results.get(i)==Relevancy.RELEVANT) {
                accum += getPrecision(i + 1);
            }
        }

        return accum/getTotalRelevantCount();
    }

    //helper method for relevant results in result set
    public int getRelevantResultsRetrievedCount(){
        int accum=0;
        for(Relevancy e:results){
            if (e==Relevancy.RELEVANT)
                accum +=1;
        }

        return accum;
    }

    //helper method for relevant results in result set
    //at k cutoff
    public int getRelevantResultsRetrievedCount(int k){
        int accum=0;
        for(int i=0;i<k;i++){
            if (results.get(i)==Relevancy.RELEVANT)
                accum +=1;
        }
        return accum;
    }

    //helper method for constructors to load result sets
    private void loadResults(int [] results){
        this.intResults = results.clone();
        this.results = new ArrayList<>();

        for (int i=0;i<results.length;i++) {
            if(results[i]==1) {
                this.results.add(i,Relevancy.RELEVANT);
                totalRelevant++;
            }
            else if(results[i]==0) {
                this.results.add(i,Relevancy.NOT_RELEVANT);
            }
            else {
                IllegalArgumentException e = new IllegalArgumentException("Invalid Relevancy Argument");
                throw e;
            }
        }
    }
}
