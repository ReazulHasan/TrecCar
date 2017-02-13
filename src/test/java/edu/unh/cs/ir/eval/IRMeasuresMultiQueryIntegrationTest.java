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



    @Test
    void testArticle2Turtle() {

        final int num_q = 1;
        final int num_ret=10;
        final int num_rel = 53;
        final int num_rel_ret = 5;
        final double map = 0.0647;
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

        String qrelsFormattedGroundTruthFileName = "data/spritzer.cbor.article.qrels";
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

        String qrelsFormattedGroundTruthFileName = "data/spritzer.cbor.hierarchical.qrels";
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

    @Test
    void selfTest1() {

        final int num_q = 70;
        final int num_ret=3463;
        final int num_rel = 174;
        final int num_rel_ret = 170;
        final double map = 0.1302;
        final double Rprec = 0.0641;
        final double recip_rank=0.1644;
        final double P_5=0.0600;
        final double P_10 =	0.0543;
        final double P_15 =	0.0590;
        final double P_20 =	0.0607;
        final double P_30 =	0.0533;
        final double P_100=	0.0243;
        final double P_200=	0.0121;
        final double P_500=	0.0049;
        final double P_1000=0.0024;

        String qrelsFormattedGroundTruthFileName = "data/spritzer.cbor.hierarchical.qrels";
        String trecFormattedResultsFileName = "results/spritzer-self-test1.run";

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
                () -> assertEquals(recip_rank, metrics.getMeanReciprocalRank(), .0001),
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
    void selfTest2() {

        final int num_q = 67;
        final int num_ret=3295;
        final int num_rel = 167;
        final int num_rel_ret = 163;
        final double map = 0.1171;
        final double Rprec = 0.0483;
        final double recip_rank=0.1504;
        final double P_5=0.0567;
        final double P_10 =	0.0507;
        final double P_15 =	0.0577;
        final double P_20 =	0.0604;
        final double P_30 =	0.0537;
        final double P_100=	0.0243;
        final double P_200=	0.0122;
        final double P_500=	0.0049;
        final double P_1000=0.0024;

        String qrelsFormattedGroundTruthFileName = "data/spritzer.cbor.hierarchical.qrels";
        String trecFormattedResultsFileName = "results/spritzer-self-test2.run";

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
                () -> assertEquals(recip_rank, metrics.getMeanReciprocalRank(), .0001),
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
    void selfTest3() {

        final int num_q = 2254;
        final int num_ret=85418;
        final int num_rel = 5336;
        final int num_rel_ret = 5129;
        final double map = 0.1645;
        final double Rprec = 0.0739;
        final double recip_rank=0.2050;
        final double P_5=0.0713;
        final double P_10 =	0.0723;
        final double P_15 =	0.0734;
        final double P_20 =	0.0703;
        final double P_30 =	0.0591;
        final double P_100=	0.0226;
        final double P_200=	0.0114;
        final double P_500=	0.0046;
        final double P_1000=0.0023;

        String qrelsFormattedGroundTruthFileName = "data/all.test200.cbor.hierarchical.qrels";
        String trecFormattedResultsFileName = "results/test200-mock1.run";

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
                () -> assertEquals(recip_rank, metrics.getMeanReciprocalRank(), .0001),
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
}