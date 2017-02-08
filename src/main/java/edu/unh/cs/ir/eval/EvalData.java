package edu.unh.cs.ir.eval;

/**
 * Created by Reazul Hasan Russel on 2/7/2017.
 */
public class EvalData {
    String id, relevance;

    public EvalData(String id, String relevance){
        this.id = id;
        this.relevance = relevance;
    }

    public String getId(){
        return id;
    }

    public String getRelevance(){
        return relevance;
    }
}
