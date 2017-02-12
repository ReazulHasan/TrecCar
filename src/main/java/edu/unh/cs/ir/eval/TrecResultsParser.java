package edu.unh.cs.ir.eval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Reazul Hasan Russel on 2/3/2017.
 */
public class TrecResultsParser {
    private static final int GROUND_TRUTH = 1, EVAL_DATA = 2;
    private static final String DEFAULT_GROUND_TRUTH = "spritzer.cbor.toplevel.qrels";
    HashMap<String, ArrayList<TrecResultsData>> mpGroundTruth, mpEvalData;

    /**
     * Create instance of evaluation by passing the file name of the data file, default ground truth will be parsed.
     * @param strEvalData
     */
    public TrecResultsParser(String strEvalData){
        mpGroundTruth = new HashMap<>();
        mpEvalData = new HashMap<>();
        readGroundTruth(DEFAULT_GROUND_TRUTH);
        readEvalData(strEvalData);
    }

    /**
     * Constructor to create an object of TrecResultsParser class with the two input file names to be evaluated
     * @param strGroundTruth
     * @param strEvalData
     */
    public TrecResultsParser(String strGroundTruth, String strEvalData){
        mpGroundTruth = new HashMap<>();
        mpEvalData = new HashMap<>();
        readGroundTruth(strGroundTruth);
        readEvalData(strEvalData);
    }

    /**
     * get the hashmap containing the parsed ground truth
     * @return a hashmap of parsed data
     */
    public HashMap<String, ArrayList<TrecResultsData>> getGroundTruth(){
        return mpGroundTruth;
    }

    /**
     * get the list of the values for the key
     * @param key
     * @return an array list containing the parsed ground truth data
     */
    public ArrayList<TrecResultsData> getGroundTruth(String key){
        return mpGroundTruth.get(key);
    }

    /**
     * get the hashmap containing the parsed data to be evaluated
     * @return a hashmap of parsed data
     */
    public HashMap<String, ArrayList<TrecResultsData>> getEvalData(){
        return mpEvalData;
    }

    /**
     * get the list of the values for the key
     * @param key
     * @return an array list containing the parsed evaluation data
     */
    public ArrayList<TrecResultsData> getEvalData(String key){
        return mpEvalData.get(key);
    }

    /**
     * Returns an arraylist of RelevancyResult objects for each query evaluated
     * in the input results trec_eval formatted file.
     *
     * @return Arraylist of all query relevancy result
     */
    public ArrayList<RelevancyResult> getRelevancyResults(){

        ArrayList<RelevancyResult> relevancyResults = new ArrayList<>();
        ArrayList<String> groundTruthResults= new ArrayList<>();

        for(String key: mpEvalData.keySet()) {
                int[] results = getResults(key);
                int totalRelevantDocuments = getCountRelevantGroundTruth(key);
                RelevancyResult relevancyResult = new RelevancyResult(key, results, totalRelevantDocuments);
                relevancyResults.add(relevancyResult);
        }

        return relevancyResults;
    }



    public int[] getResults(String topicIdentifier){

        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<String> groundTruthResults= new ArrayList<>();

        for(TrecResultsData ed : mpGroundTruth.get(topicIdentifier)){
            groundTruthResults.add(ed.getId());
        }

        for(TrecResultsData ed : mpEvalData.get(topicIdentifier)){
            if (groundTruthResults.contains(ed.getId())){
                results.add(1);
            }
            else{
                results.add(0);
            }
        }

        int[] intResults = new int[results.size()];
        for (int i = 0; i < intResults.length; i++) {
            intResults[i] = results.get(i);
        }

        return intResults;
    }

    /**
     * read ground truth data
     * @param file
     */
    private void readGroundTruth(String file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                String ar[] = line.split(" ");
//                System.out.println("Ground Data: "+line+ ", "+mpGroundTruth.size()+", "+mpEvalData.size());
                if(mpGroundTruth.containsKey(ar[0]))
                    mpGroundTruth.get(ar[0]).add(new TrecResultsData(ar[2], ar[3]));
                else{
                    mpGroundTruth.put(ar[0], new ArrayList<>());
                    mpGroundTruth.get(ar[0]).add(new TrecResultsData(ar[2], ar[3]));
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read evaluation data
     * @param file
     */
    private void readEvalData(String file){
        mpEvalData = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                //System.out.println("line: "+line);
                String ar[] = line.split("\\s+");
                //System.out.println(" ar size: "+ar.length);
//                System.out.println("Eval Data: "+line+ ", "+mpGroundTruth.size()+", "+mpEvalData.size());
                if(mpEvalData.containsKey(ar[0]))
                    mpEvalData.get(ar[0]).add(new TrecResultsData(ar[2], ar[4]));
                else{
                    mpEvalData.put(ar[0], new ArrayList<>());
                    mpEvalData.get(ar[0]).add(new TrecResultsData(ar[2], ar[4]));
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the total count of matched documents for the given query in ground truth
     * @param query
     * @return relevantCount
     */
    public int getCountRelevantGroundTruth(String query){
        return mpGroundTruth.get(query).size();
    }

    /**
     * get the total count of matched documents for the given query in evaluation data
     * @param query
     * @return relevantCount
     */
    public int getCountRelevantEvalData(String query){
        return mpEvalData.get(query).size();
    }

//    public static void main(String arg[]){
//        //System.out.println("Hello TrecResultsParser! "+arg[0]);
//        //spritzer.cbor.article.qrels results\trec_run.spritzer.cbor.article.qrels.2.test
//        if(arg.length<2){
//            System.out.println("Please enter the ground truth & evaluation file names as command line arguments");
//            System.exit(0);
//        }
//        System.out.println(arg[0]+" "+arg[1]);
//        TrecResultsParser eval = new TrecResultsParser(arg[0], arg[1]);
//        //eval.readData(arg[0], GROUND_TRUTH);
//        HashMap<String, ArrayList<TrecResultsData>> mpGt = eval.getGroundTruth();
//        HashMap<String, ArrayList<TrecResultsData>> mpEd = eval.getEvalData();
//        ArrayList<TrecResultsData> al = eval.getEvalData("Green%20sea%20turtle");//mpEd.get("Green%20sea%20turtle");
//        for(TrecResultsData ed : al){
//            System.out.println("id: "+ed.getId());
//        }
//        System.out.println("mpGt.size: "+mpGt.size()+", mpEd.size: "+mpEd.size());
//        System.out.println(eval.getRelevancyResults());
//    }
}
