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

    /*
    * Create instance of evaluation by passing the file name of the data file, default ground truth
    * will be parsed.
    * */
    public Evaluation(String strEvalData){
        mpGroundTruth = new HashMap<>();
        mpEvalData = new HashMap<>();
        readGroundTruth(DEFAULT_GROUND_TRUTH);
        readEvalData(strEvalData);
    }

    /*
    * Create instance of evaluation by passing the file name of the data file and ground truth file.
    * */
    public Evaluation(String strGroundTruth, String strEvalData){
        mpGroundTruth = new HashMap<>();
        mpEvalData = new HashMap<>();
        readGroundTruth(strGroundTruth);
        readEvalData(strEvalData);
    }

    /*
    * get the hashmap containing the parsed ground truth
    * */
    public HashMap<String, ArrayList<EvalData>> getGroundTruth(){
        return mpGroundTruth;
    }

    /*
    * get the list of the values for the key
    * */
    public ArrayList<EvalData> getGroundTruth(String key){
        return mpGroundTruth.get(key);
    }

    /*
    * get the hashmap containing the parsed data to be evaluated
    * */
    public HashMap<String, ArrayList<EvalData>> getEvalData(){
        return mpEvalData;
    }

    /*
    * get the list of the values for the key
    * */
    public ArrayList<EvalData> getEvalData(String key){
        return mpEvalData.get(key);
    }

    /*
    * Use this method if the Evaluation object is already available that contains the hashmap with ground truth.
    * only need to pass a new dataset tobe tested. pass the new data set here in this method, it will be parsed here
    * and the parsed hashmap will be available using the getter methods.
    * */
    /*public void readEvalData(String strEvalData){
        mpEvalData = new HashMap<>();
        readData(strEvalData,EVAL_DATA);
    }*/

    private void readGroundTruth(String file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                String ar[] = line.split(" ");
                System.out.println("Ground Data: "+line+ ", "+mpGroundTruth.size()+", "+mpEvalData.size());
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

    private void readEvalData(String file){
        mpEvalData = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                String ar[] = line.split("\\s+");
                System.out.println("Eval Data: "+line+ ", "+mpGroundTruth.size()+", "+mpEvalData.size());
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

    public static void main(String arg[]){
        //System.out.println("Hello Evaluation! "+arg[0]);
        Evaluation eval = new Evaluation("spritzer.cbor.article.qrels", "results.spritzer.cbor.article.qrels.test");
        //eval.readData(arg[0], GROUND_TRUTH);
        HashMap<String, ArrayList<EvalData>> mpGt = eval.getGroundTruth();
        HashMap<String, ArrayList<EvalData>> mpEd = eval.getEvalData();
        ArrayList<EvalData> al = mpEd.get("Green%20sea%20turtle");
        for(EvalData ed : al){
            System.out.println("id: "+ed.getId()+", relevance: "+ed.getRelevance());
        }
        System.out.println("mpGt.size: "+mpGt.size()+", mpEd.size: "+mpEd.size());
    }
}
