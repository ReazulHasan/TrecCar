package edu.unh.cs.ir.eval;

import java.util.ArrayList;

/**
 * This class calculates information retrieval performance measures across
 * multiple query result sets
 */
public class ClassificationAggregatedMetrics {

    private ArrayList<ClassificationBaseMetrics> results;

    /**
     *  Creates an empty class where individual results sets can be added
     *  using {@link #addMetrics(ClassificationBaseMetrics)}
     */
    public ClassificationAggregatedMetrics(){
        results=new ArrayList<ClassificationBaseMetrics>();
    }

    /** Creates a class using the passed in query result sets
     *
     * @param metrics Array list of query results
     */
    public ClassificationAggregatedMetrics(ArrayList<ClassificationBaseMetrics> metrics){
        this.results=metrics;
    }

    /**
     * Adds a query result set for analysis
     * @param results the query result set to add
     */
    public void addMetrics(ClassificationBaseMetrics results){
        this.results.add(results);
    }

    /**
     * Returns the query resultsets stored in the object
     *
     */
    public ArrayList<ClassificationBaseMetrics> getBaseMetrics(){
        return results;
    }

    /**
     * Get precision for each complete result set averaged across all result sets
     * @return the arithmetic mean of all the stored query result sets
     */
    public double getPrecision(){
        double accum =0;
        for(ClassificationBaseMetrics m: results){
            accum += m.getPrecision();
        }
        return accum/ results.size();
    }

    /**
     * Get precision at k averaged across all result sets
     * @param k the cutoff in the result sets using {@link ClassificationBaseMetrics#getPrecision(int)}
     *          Corner conditions are handled by that method.
     *
     */
    public double getPrecision(int k){
        double accum =0;
        for(ClassificationBaseMetrics m: results){
            accum += m.getPrecision(k);
        }
        return accum/ results.size();
    }

    /**
     * Get R precision at k averaged across all result sets
     * @param k the cutoff in the result sets using {@link ClassificationBaseMetrics#getPrecision(int)}
     *          Corner conditions are handled by that method.
     *
     */
    public double getRPrecision(int k){
        double accum =0;
        for(ClassificationBaseMetrics m: results){
            accum += m.getRPrecision(k);
        }
        return accum/ results.size();
    }

    /**
     * Returns the mean average precision across all result sets
     *
     */
    public double getMAP(){
        double accum =0;
        for(ClassificationBaseMetrics m: results){
            accum += m.getAveragePrecision();
        }
        return accum/ results.size();
    }
}
