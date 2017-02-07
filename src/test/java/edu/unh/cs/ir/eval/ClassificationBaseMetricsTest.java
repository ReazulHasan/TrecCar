import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by matt on 2/7/17.
 */
class ClassificationBaseMetricsTest {

    private int[] baseResults;
    private ArrayList<Relevancy> baseRelevancyResults;
    private ClassificationBaseMetrics eval;

    @Test
    void getResults() {

        assertEquals(baseRelevancyResults, eval.getResults());

    }

    @Test
    void throwsIllegalArgument(){
        int [] results ={0,1,2,1,0};
        assertThrows(IllegalArgumentException.class, () -> {new ClassificationBaseMetrics(results);});
    }

    @Test
    void getResultsSize(){
        assertEquals(10, eval.getResultsSize());
    }

    @Test
    void getTotalRelevant(){
        int totalRelevant=4;
        assertEquals(totalRelevant, eval.getTotalRelevantCount());
    }

    @Test
    void getTotalRelevant2(){
        int totalRelevant=10;
        eval = new ClassificationBaseMetrics(baseResults,totalRelevant);
        assertEquals(totalRelevant, eval.getTotalRelevantCount());
    }

    @Test
    void getPrecision(){
        double precision = 4.0/10;
        assertEquals(precision, eval.getPrecision());
    }

    @Test
    void getPrecisionAtK(){
        int k = 3;
        double precision = 2.0/3;
        assertEquals(precision, eval.getPrecision(k));
    }

    @Test
    void getPrecisionAtK2(){
        int k = 9;
        double precision = 4.0/9;
        assertEquals(precision, eval.getPrecision(k));
    }

    @Test
    void getRecall(){
        double recall = 4.0/4;
        assertEquals(recall, eval.getRecall());
    }

    @Test
    void getRecall2(){
        int totalRelevant =10;
        double recall = 4.0/totalRelevant;
        eval = new ClassificationBaseMetrics(baseResults,totalRelevant);
        assertEquals(recall, eval.getRecall());
    }

    @Test
    void getBalancedF1(){
        double precision =4.0/10;
        double recall = 4.0/4.0;
        double f1 = 2 *(precision*recall)/(precision+recall);

        assertEquals(f1, eval.getBalancedF1Score());
    }


    @BeforeEach
    void setUp() {
        baseResults = new int[]{0,1,1,0,0,0,1,1,0,0};
        baseRelevancyResults= new ArrayList<Relevancy>();
        baseRelevancyResults.add(Relevancy.NOT_RELEVANT); //0
        baseRelevancyResults.add(Relevancy.RELEVANT);    //1
        baseRelevancyResults.add(Relevancy.RELEVANT);     //2
        baseRelevancyResults.add(Relevancy.NOT_RELEVANT);  //3
        baseRelevancyResults.add(Relevancy.NOT_RELEVANT);  //4
        baseRelevancyResults.add(Relevancy.NOT_RELEVANT);  //5
        baseRelevancyResults.add(Relevancy.RELEVANT);      //6
        baseRelevancyResults.add(Relevancy.RELEVANT);      //7
        baseRelevancyResults.add(Relevancy.NOT_RELEVANT); //8
        baseRelevancyResults.add(Relevancy.NOT_RELEVANT); //9

        eval = new ClassificationBaseMetrics(baseResults);
    }
}