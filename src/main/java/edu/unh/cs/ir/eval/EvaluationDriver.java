package edu.unh.cs.ir.eval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

/**
 * Created by matt on 2/8/17.
 */
public class EvaluationDriver {

    static public void outputResults(String groundTruth, String trecFormat, ClassificationAggregatedMetrics metrics){
        System.out.println("-----------------------------------------------------------------");
        System.out.print("Results for:\n");
        System.out.println(String.format("Ground Truth File:           %s",groundTruth));
        System.out.println(String.format("Trec Formatted Results File: %s",trecFormat));
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println("Official TREC Metrics: Across All Queries");
        System.out.println("-----------------------------------------------------------------");
        System.out.println(String.format("Description                       Trec Measure     Value"));
        System.out.println(String.format("-----------                       ------------     -----"));
        System.out.println(String.format("Number of queries                 num_q            %s", metrics.getQueryCount()));
        System.out.println(String.format("Number of retrieved documents     num_ret          %s", metrics.getTotalRetrieved()));
        System.out.println(String.format("Relevant documents in corpus      num_rel          %s", metrics.getTotalRelevantInCorpus()));
        System.out.println(String.format("Relevant documents retrieved      num_rel_ret      %s", metrics.getTotalRelevantRetrieved()));
        System.out.println(String.format("Mean Average Precision            map              %.4f", metrics.getMeanAveragePrecision()));
        System.out.println(String.format("Geometric Mean Average Precision  gm_map           %.4f", metrics.getGeometricMeanAveragePrecision()));
        System.out.println(String.format("R-Precision                       Rprec            %.4f", metrics.getRPrecision()));
        System.out.println(String.format("Precision@5                       P_5              %.4f", metrics.getPrecision(5)));
        System.out.println(String.format("Precision@10                      P_10             %.4f", metrics.getPrecision(10)));
        System.out.println(String.format("Precision@15                      P_15             %.4f", metrics.getPrecision(15)));
        System.out.println(String.format("Precision@20                      P_20             %.4f", metrics.getPrecision(20)));
        System.out.println(String.format("Precision@30                      P_30             %.4f", metrics.getPrecision(30)));
        System.out.println(String.format("Precision@100                     P_100            %.4f", metrics.getPrecision(100)));
        System.out.println(String.format("Precision@200                     P_200            %.4f", metrics.getPrecision(200)));
        System.out.println(String.format("Precision@500                     P_500            %.4f", metrics.getPrecision(500)));
        System.out.println(String.format("Precision@1000                    P_1000           %.4f", metrics.getPrecision(1000)));
        System.out.println();
        System.out.println("Additional:");
        System.out.println("-----------------------------------------------------------------");
        System.out.println(String.format("Balanced F1:                                       %.4f",metrics.getMeanAverageBalancedF1()));


    }

    public static void main(String [] args){

//ToDO: Enable args
//        if(args.length<2){
//            System.out.println("Please enter the ground truth & evaluation file names as command line arguments");
//            System.exit(0);
//        }
//        System.out.println(args[0]+" "+args[1]);
//        Evaluation eval = new Evaluation(args[0], args[1]);

        String qrelsFormattedGroundTruthFileName="spritzer-v1.4/spritzer.cbor.hierarchical.qrels";
//
        String trecFormattedResultsFileName="results/results.spritzer.cbor.hierarchical.qrels.ragged.test";



        Evaluation eval = new Evaluation(qrelsFormattedGroundTruthFileName,trecFormattedResultsFileName);
        ClassificationAggregatedMetrics metrics = new ClassificationAggregatedMetrics(eval.getRelevancyResults());
        outputResults(qrelsFormattedGroundTruthFileName, trecFormattedResultsFileName,metrics);




    }
}
