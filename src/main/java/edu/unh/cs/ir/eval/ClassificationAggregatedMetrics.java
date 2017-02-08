package edu.unh.cs.ir.eval;

import java.util.ArrayList;

/**
 * Created by matt on 2/7/17.
 */
public class ClassificationAggregatedMetrics {

    private ArrayList<ClassificationBaseMetrics> results;

    public ClassificationAggregatedMetrics(){
        results=new ArrayList<ClassificationBaseMetrics>();
    }

    public ClassificationAggregatedMetrics(ArrayList<ClassificationBaseMetrics> metrics){
        this.results=metrics;
    }

    public void addMetrics(ClassificationBaseMetrics c){
        results.add(c);
    }

    public ArrayList<ClassificationBaseMetrics> getBaseMetrics(){
        return results;
    }

    //Get precision for each complete result set averaged across all result sets
    public double getPrecision(){
        double accum =0;
        for(ClassificationBaseMetrics m: results){
            accum += m.getPrecision();
        }
        return accum/ results.size();
    }

    //Get precision at k averaged across all result sets
    public double getPrecision(int k){
        double accum =0;
        for(ClassificationBaseMetrics m: results){
            accum += m.getPrecision(k);
        }
        return accum/ results.size();
    }

    //Get R precision at k averaged across all result sets
    public double getRPrecision(int k){
        double accum =0;
        for(ClassificationBaseMetrics m: results){
            accum += m.getRPrecision(k);
        }
        return accum/ results.size();
    }

    //Get mean average precision across all result sets
    public double getMAP(){
        double accum =0;
        for(ClassificationBaseMetrics m: results){
            accum += m.getAveragePrecision();
        }
        return accum/ results.size();
    }
}
