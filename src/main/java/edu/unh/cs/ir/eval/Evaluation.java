package edu.unh.cs.ir.eval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Reazul Hasan Russel on 2/3/2017.
 */
public class Evaluation {
    private static final int GROUND_TRUTH = 1, EVAL_DATA = 2;
    private static final String DEFAULT_GROUND_TRUTH = "spritzer.cbor.toplevel.qrels";
    HashMap<String, ArrayList<EvalData>> mpGroundTruth, mpEvalData;

    /**
     * Create instance of evaluation by passing the file name of the data file, default ground truth will be parsed.
     * @param strEvalData
     */
    public Evaluation(String strEvalData){
        mpGroundTruth = new HashMap<>();
        mpEvalData = new HashMap<>();
        readGroundTruth(DEFAULT_GROUND_TRUTH);
        readEvalData(strEvalData);
    }

    /**
     * Constructor to create an object of Evaluation class with the two input file names to be evaluated
     * @param strGroundTruth
     * @param strEvalData
     */
    public Evaluation(String strGroundTruth, String strEvalData){
        mpGroundTruth = new HashMap<>();
        mpEvalData = new HashMap<>();
        readGroundTruth(strGroundTruth);
        readEvalData(strEvalData);
    }

    /**
     * get the hashmap containing the parsed ground truth
     * @return a hashmap of parsed data
     */
    public HashMap<String, ArrayList<EvalData>> getGroundTruth(){
        return mpGroundTruth;
    }

    /**
     * get the list of the values for the key
     * @param key
     * @return an array list containing the parsed ground truth data
     */
    public ArrayList<EvalData> getGroundTruth(String key){
        return mpGroundTruth.get(key);
    }

    /**
     * get the hashmap containing the parsed data to be evaluated
     * @return a hashmap of parsed data
     */
    public HashMap<String, ArrayList<EvalData>> getEvalData(){
        return mpEvalData;
    }

    /**
     * get the list of the values for the key
     * @param key
     * @return an array list containing the parsed evaluation data
     */
    public ArrayList<EvalData> getEvalData(String key){
        return mpEvalData.get(key);
    }

    /**
     * get the relevancy result for a topic identifier
     * @param topicIdentifier
     * @return An int array
     */
    public int[] getRelevancyResults(String topicIdentifier){

        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<String> groundTruthResults= new ArrayList<>();

        for(EvalData ed : mpGroundTruth.get(topicIdentifier)){
            groundTruthResults.add(ed.getId());
        }

        for(EvalData ed : mpEvalData.get(topicIdentifier)){
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
                    mpGroundTruth.get(ar[0]).add(new EvalData(ar[2], ar[3]));
                else{
                    mpGroundTruth.put(ar[0], new ArrayList<>());
                    mpGroundTruth.get(ar[0]).add(new EvalData(ar[2], ar[3]));
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
                String ar[] = line.split("\\s+");
//                System.out.println("Eval Data: "+line+ ", "+mpGroundTruth.size()+", "+mpEvalData.size());
                if(mpEvalData.containsKey(ar[0]))
                    mpEvalData.get(ar[0]).add(new EvalData(ar[2], ar[4]));
                else{
                    mpEvalData.put(ar[0], new ArrayList<>());
                    mpEvalData.get(ar[0]).add(new EvalData(ar[2], ar[4]));
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

    public static void main(String arg[]){
        //System.out.println("Hello Evaluation! "+arg[0]);
        Evaluation eval = new Evaluation("spritzer.cbor.article.qrels", "results.spritzer.cbor.article.qrels.test");
        //eval.readData(arg[0], GROUND_TRUTH);
        HashMap<String, ArrayList<EvalData>> mpGt = eval.getGroundTruth();
        HashMap<String, ArrayList<EvalData>> mpEd = eval.getEvalData();
        ArrayList<EvalData> al = mpEd.get("Green%20sea%20turtle");
        for(EvalData ed : al){
            System.out.println("id: "+ed.getId());
        }
        System.out.println("mpGt.size: "+mpGt.size()+", mpEd.size: "+mpEd.size());
    }
}
