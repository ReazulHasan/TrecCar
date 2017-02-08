package edu.unh.cs.ir.eval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

/**
 * Created by matt on 2/8/17.
 */
public class EvaluationDriver {

    static public void outputResults(String groundTruth, String trecFormat, String topicId,
                              ClassificationBaseMetrics performance, int[] results){
        System.out.println("---------------------------------------------");
        System.out.print("Results for:");
        System.out.println(String.format("Ground Truth: %s",groundTruth));
        System.out.println(String.format("Trec Formatted Results: %s",trecFormat));
        System.out.println(String.format("Topic Id: %s",topicId));
        System.out.println("---------------------------------------------");
        System.out.println();
        System.out.println("Results Relevancy List");
        System.out.println("---------------------------------------------");
        System.out.println(Arrays.toString(results));
        System.out.println("---------------------------------------------");
        System.out.println();
        System.out.println("Performance:");
        System.out.println("---------------------------------------------");
        System.out.println(String.format("Precison: %s",performance.getPrecision()));
        System.out.println(String.format("Precison@5: %s",performance.getPrecision(5)));
        System.out.println(String.format("Recall: %s",performance.getRecall()));
        System.out.println(String.format("Balanced F1: %s",performance.getBalancedF1Score()));
        System.out.println(String.format("Average Precision: %s",performance.getAveragePrecision()));
    }

    public static void main(String [] args){
        String qrelsFormattedGroundTruthFileName="spritzer.cbor.article.qrels";
        String trecFormattedResultsFileName="results.spritzer.cbor.article.qrels.test";
        String topicId="Green%20sea%20turtle";

        Evaluation eval = new Evaluation(qrelsFormattedGroundTruthFileName,trecFormattedResultsFileName);
        int [] results = eval.getRelevancyResults(topicId);

        ClassificationBaseMetrics performance = new ClassificationBaseMetrics(results);

        outputResults(qrelsFormattedGroundTruthFileName, trecFormattedResultsFileName,topicId,performance,results);

        //Print results




    }
}
