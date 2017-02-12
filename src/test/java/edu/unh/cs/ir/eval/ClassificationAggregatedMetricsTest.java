package edu.unh.cs.ir.eval;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by matt on 2/7/17.
 */
class ClassificationAggregatedMetricsTest {
    int[] results1;
    int[] results2;
    int[] results3;

    private ArrayList<Relevancy> resultsRelevancy1;
    private ArrayList<Relevancy> resultsRelevancy2;
    private ArrayList<Relevancy> resultsRelevancy3;

    private ClassificationBaseMetrics eval1;
    private ClassificationBaseMetrics eval2;
    private ClassificationBaseMetrics eval3;

    ClassificationAggregatedMetrics evals;

    @BeforeEach
    void setUp() {

        results1 = new int[]{0,1,1,0,0,0,1,1,0,0};
        resultsRelevancy1= new ArrayList<Relevancy>();
        resultsRelevancy1.add(Relevancy.NOT_RELEVANT); //0
        resultsRelevancy1.add(Relevancy.RELEVANT);    //1
        resultsRelevancy1.add(Relevancy.RELEVANT);     //2
        resultsRelevancy1.add(Relevancy.NOT_RELEVANT);  //3
        resultsRelevancy1.add(Relevancy.NOT_RELEVANT);  //4
        resultsRelevancy1.add(Relevancy.NOT_RELEVANT);  //5
        resultsRelevancy1.add(Relevancy.RELEVANT);      //6
        resultsRelevancy1.add(Relevancy.RELEVANT);      //7
        resultsRelevancy1.add(Relevancy.NOT_RELEVANT); //8
        resultsRelevancy1.add(Relevancy.NOT_RELEVANT); //9

        results2 = new int[]{1,1,1,0,1,0,1,0,0,0};
        resultsRelevancy2= new ArrayList<Relevancy>();
        resultsRelevancy2.add(Relevancy.RELEVANT); //0
        resultsRelevancy2.add(Relevancy.RELEVANT);    //1
        resultsRelevancy2.add(Relevancy.RELEVANT);     //2
        resultsRelevancy2.add(Relevancy.NOT_RELEVANT);  //3
        resultsRelevancy2.add(Relevancy.RELEVANT);  //4
        resultsRelevancy2.add(Relevancy.NOT_RELEVANT);  //5
        resultsRelevancy2.add(Relevancy.RELEVANT);      //6
        resultsRelevancy2.add(Relevancy.NOT_RELEVANT);      //7
        resultsRelevancy2.add(Relevancy.NOT_RELEVANT); //8
        resultsRelevancy2.add(Relevancy.NOT_RELEVANT); //9

        results3 = new int[]{1,0,1,0,1,1,1,0,0,1};
        resultsRelevancy3= new ArrayList<Relevancy>();
        resultsRelevancy3.add(Relevancy.RELEVANT); //0
        resultsRelevancy3.add(Relevancy.NOT_RELEVANT);    //1
        resultsRelevancy3.add(Relevancy.RELEVANT);     //2
        resultsRelevancy3.add(Relevancy.NOT_RELEVANT);  //3
        resultsRelevancy3.add(Relevancy.RELEVANT);  //4
        resultsRelevancy3.add(Relevancy.RELEVANT);  //5
        resultsRelevancy3.add(Relevancy.RELEVANT);      //6
        resultsRelevancy3.add(Relevancy.NOT_RELEVANT);      //7
        resultsRelevancy3.add(Relevancy.NOT_RELEVANT); //8
        resultsRelevancy3.add(Relevancy.RELEVANT); //9

        eval1 = new ClassificationBaseMetrics(results1);
        eval2 = new ClassificationBaseMetrics(results2);
        eval3 = new ClassificationBaseMetrics(results3);

        ArrayList<ClassificationBaseMetrics> evalList = new ArrayList<>();
        evalList.add(eval1);
        evalList.add(eval2);
        evalList.add(eval3);

        RelevancyResult r1 = new RelevancyResult("q1",results1,4);
        RelevancyResult r2 = new RelevancyResult("q2",results2,5);
        RelevancyResult r3 = new RelevancyResult("q3",results3,6);

        ArrayList<RelevancyResult> relevancyResults = new ArrayList<>();
        relevancyResults.add(r1);
        relevancyResults.add(r2);
        relevancyResults.add(r3);

         evals= new ClassificationAggregatedMetrics(relevancyResults);
    }

    @Test
    void testBaseMetrics() {

        ClassificationBaseMetrics c1 = evals.getBaseMetrics("q1");
        assertArrayEquals(results1,c1.getIntResults());
        assertArrayEquals(results2,evals.getBaseMetrics("q2").getIntResults());
        assertArrayEquals(results3,evals.getBaseMetrics("q3").getIntResults());
    }

    @Test
    void getPrecision(){
        double p1 = 4.0/10;
        double p2 = 5.0/10;
        double p3 = 6.0/10;

        double precision = (p1+p2+p3)/3;

        assertEquals(precision,evals.getPrecision());
    }
    @Test
    void getPrecisionAtK(){
        int k=5;

        double p1 = 2.0/k;
        double p2 = 4.0/k;
        double p3 = 3.0/k;

        double precision = (p1+p2+p3)/3;

        assertEquals(precision,evals.getPrecision(k));
    }



    @Test
    void getRPrecision(){

        double p1 = 2.0/4.0;
        double p2 = 4.0/5.0;
        double p3 = 4.0/6.0;

        double rPrecision = (p1+p2+p3)/3;

        assertEquals(rPrecision,evals.getRPrecision());
    }

    @Test
    void getRPrecision2(){


        int relevant1=20;
        int relevant2=30;
        int relevant3=100;

        double p1 = 4.0/relevant1;
        double p2 = 5.0/relevant2;
        double p3 = 6.0/relevant3;

        double rPrecision = (p1+p2+p3)/3;

        eval1 = new ClassificationBaseMetrics(results1,relevant1);
        eval2 = new ClassificationBaseMetrics(results2, relevant2);
        eval3 = new ClassificationBaseMetrics(results3, relevant3);

        evals= new ClassificationAggregatedMetrics();
        evals.addMetrics(eval1);
        evals.addMetrics(eval2);
        evals.addMetrics(eval3);

        assertEquals(rPrecision,evals.getRPrecision());
    }



    //Worked example from https://www.youtube.com/watch?v=pM6DJ0ZZee0
    @Test
    void getMAP(){
        int[] results1 = {1,0,1,0,0,1,0,0,1,1};
        int[] results2 = {0,1,0,0,1,0,1,0,0,0};

        double map = 0.53;

        eval1 = new ClassificationBaseMetrics(results1);
        eval2 = new ClassificationBaseMetrics(results2);

        evals= new ClassificationAggregatedMetrics();
        evals.addMetrics(eval1);
        evals.addMetrics(eval2);

        assertEquals(map,evals.getMeanAveragePrecision(),0.01);
    }

}