package edu.unh.cs.ir.eval;

import java.util.ArrayList;

/**
 * This class calculates information retrieval performance measures across
 * multiple query result sets
 */
public class IRMeasuresMultiQuery {

    private ArrayList<IRMeasuresSingleQuery> results;

    /**
     *  Creates an empty class where individual results sets can be added
     *  using {@link #addMetrics(IRMeasuresSingleQuery)}
     */
    public IRMeasuresMultiQuery(){
        results=new ArrayList<IRMeasuresSingleQuery>();
    }

    /** Creates a class using the passed in query result sets
     *
     * @param metrics Array list of query results
     */
    //Todo: Remove all tests using this constructor then delete
//    public IRMeasuresMultiQuery(ArrayList<IRMeasuresSingleQuery> metrics){
//        this.results=metrics;
//    }

    /**
     * Creates a class using a passed in ArrayList of {@link RelevancyResult} objects
     *
     */
    public IRMeasuresMultiQuery(ArrayList<RelevancyResult> relevancyResults){
        this.results = new ArrayList<>();

        for(RelevancyResult relevancyResult: relevancyResults){
            results.add(new IRMeasuresSingleQuery(relevancyResult));
        }


    }

    /**
     * Adds a query result set for analysis
     * @param results the query result set to add
     */
    public void addMetrics(IRMeasuresSingleQuery results){
        this.results.add(results);
    }

    /**
     * Returns the query resultsets stored in the object
     *
     */
    public IRMeasuresSingleQuery getBaseMetrics(String query){
        for(IRMeasuresSingleQuery m: results){
            if (m.getQuery().contentEquals(query))
                return m;
        }
        return null;
    }


    /**
     * Returns a count of distinct queries in the result set
     * @return count of total number of queries
     */
    public int getQueryCount(){
        return results.size();
    }

    /**
     * Returns the total number of relevant documents across all queries in
     * the corpus
     * @return count of relevant corpus documents
     */
    public int getTotalRelevantInCorpus(){

        int accum =0;
        for(IRMeasuresSingleQuery metrics : results){
            accum+=metrics.getTotalRelevantCount();
        }
        return accum;
    }

    /**
     * Returns the total number of retrieved documents across all queries
     * @return count of retrieved documents
     */
    public int getTotalRetrieved(){

        int accum =0;
        for(IRMeasuresSingleQuery metrics : results){
            accum+=metrics.getResultsSize();
        }
        return accum;
    }
    /**
     * Returns the total number of relevant documents across all queries in
     * the resultsset
     * @return count of relevant corpus documents
     */
    public int getTotalRelevantRetrieved(){

        int accum =0;
        for(IRMeasuresSingleQuery metrics : results){
            accum+=metrics.getRelevantResultsRetrievedCount();
        }
        return accum;
    }

    /**
     * Get precision for each complete result set averaged across all result sets
     * @return the arithmetic mean of all the stored query result sets
     */
    public double getPrecision(){
        double accum =0;
        for(IRMeasuresSingleQuery m: results){
            accum += m.getPrecision();
        }
        return accum/ results.size();
    }

    /**
     * Get precision at k averaged across all result sets
     * @param k the cutoff in the result sets using {@link IRMeasuresSingleQuery#getPrecision(int)}
     *          Corner conditions are handled by that method.
     *
     */
    public double getPrecision(int k){
        double accum =0;
        for(IRMeasuresSingleQuery m: results){
            accum += m.getPrecision(k);
        }
        return accum/ results.size();
    }

    /**
     * Get R precision averaged across all result sets
     *
     */
    public double getRPrecision(){
        double accum =0;
        for(IRMeasuresSingleQuery m: results){
            accum += m.getRPrecision();
        }
        return accum/ results.size();
    }

    /**
     * Returns the arithmetic mean average precision across all result sets
     *
     */
    public double getMeanAveragePrecision(){
        double accum =0;
        for(IRMeasuresSingleQuery m: results){
            accum += m.getAveragePrecision();
        }
        return accum/ results.size();
    }

    /**
     * Returns the geometric mean average precision across all result sets.
     * This is the same measure as getMeanAveragePrecision on an
     * individual query.  The geometric mean rewards methods that are more consistent over queries
     * as opposed to methods which do very well for some queries but poorly on other queries.
     *
     */
    public double getGeometricMeanAveragePrecision(){
        double accum =1;
        for(IRMeasuresSingleQuery m: results){
            accum *= m.getAveragePrecision();
        }
        return Math.pow(accum, 1.0/getQueryCount());
    }

    public double getMeanAverageRecall(){
        double accum =0;
        for(IRMeasuresSingleQuery m: results){
            accum += m.getRecall();
        }
        return (double) accum/getQueryCount();
    }

    public double getMeanAverageBalancedF1(){
        double accum =0;
        for(IRMeasuresSingleQuery m: results){
            accum += m.getBalancedF1Score();
        }
        return (double) accum/getQueryCount();
    }

}
