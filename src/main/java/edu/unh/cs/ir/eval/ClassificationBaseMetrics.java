package edu.unh.cs.ir.eval;

import java.util.ArrayList;

/**
 * Created by matt on 2/7/17.
 */
public class ClassificationBaseMetrics {

    private ArrayList<Relevancy> results;
    private int totalRelevant=0;


    public ClassificationBaseMetrics(int [] results) {

        loadResults(results);
    }

    public ClassificationBaseMetrics(int [] results, int totalRelevant) {

        loadResults(results);

        //Overwrites relevant from results set
        this.totalRelevant=totalRelevant;
    }

    public int getTotalRelevantCount() {
        return totalRelevant;
    }

    public ArrayList<Relevancy> getResults() {
        return results;
    }

    public int getResultsSize(){
        return results.size();
    }

    //Returns precision for entire result set
    public double getPrecision(){

        int accum = getRelevantResultsCount();
        return (double)accum/ getResultsSize();
    }

    //Returns precision for precision@k
    public double getPrecision(int k){

        if(k> getResultsSize())
            k= getResultsSize();

        int accum = getRelevantResultsCount(k);

        return (double)accum/ k;
    }

    //Returns recall
    public double getRecall(){
        return (double) getRelevantResultsCount()/getTotalRelevantCount();
    }

    //Returns balanced f score for the result set
    public double getBalancedF1Score(){
        double precision = getPrecision();
        double recall = getRecall();

        return 2 * (precision*recall)/(precision+recall);
    }

    //Gets R-Precision
    public double getRPrecision(int k){
        return (double) getRelevantResultsCount(k)/getTotalRelevantCount();
    }

    //Gets average precision
    public double getAveragePrecision(){
        double accum =0;

        for(int i=0;i< getResultsSize();i++){
            if(results.get(i)==Relevancy.RELEVANT) {
                accum += getPrecision(i + 1);
            }
        }

        return accum/getRelevantResultsCount();
    }

    //helper method for relevant results in result set
    private int getRelevantResultsCount(){
        int accum=0;
        for(Relevancy e:results){
            if (e==Relevancy.RELEVANT)
                accum +=1;
        }

        return accum;
    }

    //helper method for relevant results in result set
    //at k cutoff
    private int getRelevantResultsCount(int k){
        int accum=0;
        for(int i=0;i<k;i++){
            if (results.get(i)==Relevancy.RELEVANT)
                accum +=1;
        }
        return accum;
    }

    //helper method for constructors to load result sets
    private void loadResults(int [] results){
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
