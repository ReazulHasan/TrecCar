package edu.unh.cs.ir.eval;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by matt on 2/8/17.
 */
class EvaluationTest {
    private Evaluation eval;

    @BeforeEach
    void setUp() {
        eval = new Evaluation("spritzer.cbor.article.qrels",
                "results.spritzer.cbor.article.qrels.test");
    }

    @Test
    void getRelevancyResults() {
        String topicIdentifier = "Green%20sea%20turtle";
        int [] relevancyResults ={1,1,1,1,1,1,1,0,0,0,0,0,0,0};

        assertArrayEquals(relevancyResults,eval.getRelevancyResults(topicIdentifier));


    }

}