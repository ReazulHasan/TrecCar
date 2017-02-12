package edu.unh.cs.ir.eval;

/**
 * Created by matt on 2/8/17.
 */
public class Evaluation {

    static public void outputResults(String groundTruth, String trecFormat, IRMeasuresMultiQuery metrics){
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
        System.out.println(String.format("R-Precision                       Rprec            %.4f", metrics.getRPrecision()));
        System.out.println(String.format("Mean Reciprocal Rank (MRR):       recip_rank       %.4f",metrics.getMeanReciprocalRank()));
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


        if(args.length<2){
            System.out.println("Please enter the ground truth & evaluation file names as command line arguments");
            System.exit(0);
        }


//        String qrelsFormattedGroundTruthFileName="spritzer-v1.4/spritzer.cbor.hierarchical.qrels";
        String qrelsFormattedGroundTruthFileName=args[0];
////
        String trecFormattedResultsFileName=args[1];
//       String trecFormattedResultsFileName="results/results.spritzer.cbor.hierarchical.qrels.ragged.test";





        try {
            TrecResultsParser eval = new TrecResultsParser(qrelsFormattedGroundTruthFileName,trecFormattedResultsFileName);
            IRMeasuresMultiQuery metrics = new IRMeasuresMultiQuery(eval.getRelevancyResults());
            outputResults(qrelsFormattedGroundTruthFileName, trecFormattedResultsFileName,metrics);
        }catch (Exception e){
            System.out.println(String.format("Program execution error %s",e));
            System.out.println("Make sure your file arguments are <qrels file> <trec formatted results file>");

        }






    }
}
