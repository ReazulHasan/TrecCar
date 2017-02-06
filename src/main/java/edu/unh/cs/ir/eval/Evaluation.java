package main.java.edu.unh.cs.ir.eval;

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
    HashMap<String, ArrayList<String>> mpGroundTruth, mpEvalData;

    /*
    * Create instance of evaluation by passing the file name of the data file, default ground truth
    * will be parsed.
    * */
    public Evaluation(String strEvalData){
        mpGroundTruth = new HashMap<>();
        mpEvalData = new HashMap<>();
        readData(DEFAULT_GROUND_TRUTH, GROUND_TRUTH);
        readData(strEvalData, EVAL_DATA);
    }

    /*
    * Create instance of evaluation by passing the file name of the data file and ground truth file.
    * */
    public Evaluation(String strGroundTruth, String strEvalData){
        mpGroundTruth = new HashMap<>();
        mpEvalData = new HashMap<>();
        readData(strGroundTruth, GROUND_TRUTH);
        readData(strEvalData, EVAL_DATA);
    }

    /*
    * get the hashmap containing the parsed ground truth
    * */
    public HashMap<String, ArrayList<String>> getGroundTruth(){
        return mpGroundTruth;
    }

    /*
    * get the list of the values for the key
    * */
    public ArrayList<String> getGroundTruth(String key){
        return mpGroundTruth.get(key);
    }

    /*
    * get the hashmap containing the parsed data to be evaluated
    * */
    public HashMap<String, ArrayList<String>> getEvalData(){
        return mpEvalData;
    }

    /*
    * get the list of the values for the key
    * */
    public ArrayList<String> getEvalData(String key){
        return mpEvalData.get(key);
    }

    /*
    * Use this method if the Evaluation object is already available that contains the hashmap with ground truth.
    * only need to pass a new dataset tobe tested. pass the new data set here in this method, it will be parsed here
    * and the parsed hashmap will be available using the getter methods.
    * */
    public void readEvalData(String strEvalData){
        mpEvalData = new HashMap<>();
        readData(strEvalData,EVAL_DATA);
    }

    /*
    * This method is used to read and parse the data.
    * */
    private void readData(String file, int what){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                String ar[] = line.split(" ");
                if(ar[3].equals("1") && what==GROUND_TRUTH) {
                    System.out.println("Ground Data: "+line+ ", "+mpGroundTruth.size()+", "+mpEvalData.size());
                    if(mpGroundTruth.containsKey(ar[0]))
                        mpGroundTruth.get(ar[0]).add(ar[2]);
                    else{
                        mpGroundTruth.put(ar[0], new ArrayList<>());
                        mpGroundTruth.get(ar[0]).add(ar[2]);
                    }
                }
                else if(ar[3].equals("1") && what==EVAL_DATA) {
                    System.out.println("Eval Data: "+line+ ", "+mpGroundTruth.size()+", "+mpEvalData.size());
                    if(mpEvalData.containsKey(ar[0]))
                        mpEvalData.get(ar[0]).add(ar[2]);
                    else{
                        mpEvalData.put(ar[0], new ArrayList<>());
                        mpEvalData.get(ar[0]).add(ar[2]);
                    }
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String arg[]){
        System.out.println("Hello Evaluation! "+arg[0]);
        Evaluation eval = new Evaluation(arg[0], "spritzer.cbor.toplevel.eval.qrels");
        //eval.readData(arg[0], GROUND_TRUTH);
        HashMap<String, ArrayList<String>> mpGt = eval.getGroundTruth();
        HashMap<String, ArrayList<String>> mpEd = eval.getEvalData();
        System.out.println("mpGt.size: "+mpGt.size()+", mpEd.size: "+mpEd.size());
    }
}
