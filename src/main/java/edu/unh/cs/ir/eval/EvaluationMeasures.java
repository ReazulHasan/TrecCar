package edu.unh.cs.ir.eval;
// Starter file for evaluation measures


public class EvaluationMeasures {

    private int falsePositives;
    private int falseNegatives;
    private int truePositives;
    private int trueNegatives;

    public EvaluationMeasures(int falseNegatives){

        this.falseNegatives=falseNegatives;

        //falseNegatives,falsePositives, truePositives, trueNegatives =0;
    }

    public int getFalseNegatives() {
        return falseNegatives;
    }
}
