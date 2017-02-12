package edu.unh.cs.ir.eval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by matt on 2/7/17.
 */
class IRMeasuresMultiQueryIntegrationTest {
    private TrecResultsParser eval;

    private ArrayList<RelevancyResult> spritzerCborArticleQrels2;
    private IRMeasuresMultiQuery metrics;
    private String nukeArticle2Query;
    private String turtleArticle2Query;
    private String biofuelArticle2Query;
    private int [] turtleArticle2Results;
    private int [] nukeArticle2Results;
    private int [] biofuelArticle2Results;

    private int nukeArticleTotalRelevant;
    private int biofuelArticleTotalRelevant;
    private int turtleArticleTotalRelevant;

    private RelevancyResult turtleArticle2RelevancyResults;
    private RelevancyResult biofuelArticle2RelevancyResults;
    private RelevancyResult nukeArticle2RelevancyResults;

    @BeforeEach
    void setUp() {
//        eval = new TrecResultsParser("spritzer.cbor.article.qrels",
//                "results.spritzer.cbor.article.qrels.test");
//
//
//        nukeArticle2Query = "Behavior%20of%20nuclear%20fuel%20during%20a%20reactor%20accident";
//        turtleArticle2Query = "Green%20sea%20turtle";
//        biofuelArticle2Query = "Sustainable%20biofuel";
//
//        nukeArticle2Results = new int[]{0, 1, 1, 0, 0, 1, 0, 0, 0, 1};
//        turtleArticle2Results = new int[]{1, 1, 0, 0, 0, 0, 1, 1, 0, 1};
//        biofuelArticle2Results = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
//
//        turtleArticleTotalRelevant = 53;
//        nukeArticleTotalRelevant = 52;
//        biofuelArticleTotalRelevant = 41;
//
//        nukeArticle2RelevancyResults = new RelevancyResult(nukeArticle2Query, nukeArticle2Results, nukeArticleTotalRelevant);
//        turtleArticle2RelevancyResults = new RelevancyResult(turtleArticle2Query, turtleArticle2Results, turtleArticleTotalRelevant);
//        biofuelArticle2RelevancyResults = new RelevancyResult(biofuelArticle2Query, biofuelArticle2Results, biofuelArticleTotalRelevant);


    }

    @Test
    void testArticle2Turtle() {

        final int num_q = 1;
        final int num_ret=10;
        final int num_rel = 53;
        final int num_rel_ret = 5;
        final double map = 0.0647;
        final double gm_map = 0.0647;
        final double Rprec = 0.0943;
        final double P_5=0.4000;
        final double P_10 =	0.5000;
        final double P_15 =	0.3333;
        final double P_20 =	0.2500;
        final double P_30 =	0.1667;
        final double P_100=	0.0500;
        final double P_200=	0.0250;
        final double P_500=	0.0100;
        final double P_1000=0.0050;

        String qrelsFormattedGroundTruthFileName = "spritzer-v1.4/spritzer.cbor.article.qrels";
        String trecFormattedResultsFileName = "results/results.spritzer.cbor.article.qrels.2.turtle.test";

        eval = new TrecResultsParser(qrelsFormattedGroundTruthFileName, trecFormattedResultsFileName);
        ArrayList<RelevancyResult> relevancyResults = eval.getRelevancyResults();

        IRMeasuresMultiQuery metrics = new IRMeasuresMultiQuery(relevancyResults);

        Assertions.assertAll(
            () -> assertEquals(num_q, metrics.getQueryCount()),
            () -> assertEquals(num_rel, metrics.getTotalRelevantInCorpus()),
            () -> assertEquals(num_ret, metrics.getTotalRetrieved()),
            () -> assertEquals(num_rel_ret, metrics.getTotalRelevantRetrieved()),
            () -> assertEquals(map, metrics.getMeanAveragePrecision(), 0.001),
            () -> assertEquals(Rprec, metrics.getRPrecision(), .0001),
                () -> assertEquals(P_5, metrics.getPrecision(5), .0001),
                () -> assertEquals(P_10, metrics.getPrecision(10), .0001),
                () -> assertEquals(P_15, metrics.getPrecision(15), .0001),
                () -> assertEquals(P_20, metrics.getPrecision(20), .0001),
                () -> assertEquals(P_30, metrics.getPrecision(30), .0001),
                () -> assertEquals(P_100, metrics.getPrecision(100), .0001),
                () -> assertEquals(P_200, metrics.getPrecision(200), .0001),
                () -> assertEquals(P_500, metrics.getPrecision(500), .0001),
                () -> assertEquals(P_1000, metrics.getPrecision(1000), .0001)

        );

    }

    @Test
    void testHierarchialRagged() {

        final int num_q = 5;
        final int num_ret=14;
        final int num_rel = 25;
        final int num_rel_ret = 9;
        final double map = 0.3905;
        final double gm_map = 0.0423;
        final double Rprec = 0.3905;
        final double P_5=0.36;
        final double P_10 =	0.18;
        final double P_15 =	0.12;
        final double P_20 =	0.09;
        final double P_30 =	0.06;
        final double P_100=	0.018;
        final double P_200=	0.009;
        final double P_500=	0.0036;
        final double P_1000=0.0018;

        String qrelsFormattedGroundTruthFileName = "spritzer-v1.4/spritzer.cbor.hierarchical.qrels";
        String trecFormattedResultsFileName = "results/results.spritzer.cbor.hierarchical.qrels.ragged.test";

        eval = new TrecResultsParser(qrelsFormattedGroundTruthFileName, trecFormattedResultsFileName);
        ArrayList<RelevancyResult> relevancyResults = eval.getRelevancyResults();

        IRMeasuresMultiQuery metrics = new IRMeasuresMultiQuery(relevancyResults);

        Assertions.assertAll(
                () -> assertEquals(num_q, metrics.getQueryCount()),
                () -> assertEquals(num_rel, metrics.getTotalRelevantInCorpus()),
                () -> assertEquals(num_ret, metrics.getTotalRetrieved()),
                () -> assertEquals(num_rel_ret, metrics.getTotalRelevantRetrieved()),
                () -> assertEquals(map, metrics.getMeanAveragePrecision(), 0.001),
                () -> assertEquals(Rprec, metrics.getRPrecision(), .0001),
                () -> assertEquals(P_5, metrics.getPrecision(5), .0001),
                () -> assertEquals(P_10, metrics.getPrecision(10), .0001),
                () -> assertEquals(P_15, metrics.getPrecision(15), .0001),
                () -> assertEquals(P_20, metrics.getPrecision(20), .0001),
                () -> assertEquals(P_30, metrics.getPrecision(30), .0001),
                () -> assertEquals(P_100, metrics.getPrecision(100), .0001),
                () -> assertEquals(P_200, metrics.getPrecision(200), .0001),
                () -> assertEquals(P_500, metrics.getPrecision(500), .0001),
                () -> assertEquals(P_1000, metrics.getPrecision(1000), .0001)

        );

    }
//    void testArticle2Turtle() {
//
//        final int num_rel=146;
//
//        String qrelsFormattedGroundTruthFileName="spritzer.cbor.article.qrels";
//        String trecFormattedResultsFileName="results/results.spritzer.cbor.article.qrels.2.test";
//
//        eval = new TrecResultsParser(qrelsFormattedGroundTruthFileName,trecFormattedResultsFileName);
//        ArrayList<RelevancyResult> relevancyResults = eval.getRelevancyResults();
//
//        IRMeasuresMultiQuery metrics = new IRMeasuresMultiQuery(relevancyResults);
//
//        assertEquals(num_rel,metrics.getTotalRelevantDocuments());
//    }
}