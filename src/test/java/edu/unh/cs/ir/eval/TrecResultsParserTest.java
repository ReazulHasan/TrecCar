package edu.unh.cs.ir.eval;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by matt on 2/8/17.
 */
class TrecResultsParserTest {
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
        eval = new TrecResultsParser("data/spritzer.cbor.article.qrels",
                "results/results.spritzer.cbor.article.qrels.1.test");


        nukeArticle2Query = "Behavior%20of%20nuclear%20fuel%20during%20a%20reactor%20accident";
        turtleArticle2Query = "Green%20sea%20turtle";
        biofuelArticle2Query = "Sustainable%20biofuel";

        nukeArticle2Results = new int[]{0, 1, 1, 0, 0, 1, 0, 0, 0, 1};
        turtleArticle2Results = new int[]{1, 1, 0, 0, 0, 0, 1, 1, 0, 1};
        biofuelArticle2Results = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1};

        turtleArticleTotalRelevant = 53;
        nukeArticleTotalRelevant = 52;
        biofuelArticleTotalRelevant = 41;

        nukeArticle2RelevancyResults = new RelevancyResult(nukeArticle2Query, nukeArticle2Results, nukeArticleTotalRelevant);
        turtleArticle2RelevancyResults = new RelevancyResult(turtleArticle2Query, turtleArticle2Results, turtleArticleTotalRelevant);
        biofuelArticle2RelevancyResults = new RelevancyResult(biofuelArticle2Query, biofuelArticle2Results, biofuelArticleTotalRelevant);


    }

    @Test
    void article2RelevancyResponse() {

        String qrelsFormattedGroundTruthFileName="data/spritzer.cbor.article.qrels";
        String trecFormattedResultsFileName="results/results.spritzer.cbor.article.qrels.2.test";

        eval = new TrecResultsParser(qrelsFormattedGroundTruthFileName,trecFormattedResultsFileName);
        ArrayList<RelevancyResult> relevancyResults = eval.getRelevancyResults();

        //should return 3 query RelevancyResult objects
        assertEquals(3,relevancyResults.size());

        for(RelevancyResult relevancyResult:relevancyResults){
            if(relevancyResult.getQuery().contentEquals(turtleArticle2Query)){
                assertArrayEquals(turtleArticle2Results,relevancyResult.getResults());
                assertEquals(turtleArticleTotalRelevant,relevancyResult.getTotalRelevantDocuments());
            }

            if(relevancyResult.getQuery().contentEquals(nukeArticle2Query)){
                assertArrayEquals(nukeArticle2Results,relevancyResult.getResults());
                assertEquals(nukeArticleTotalRelevant,relevancyResult.getTotalRelevantDocuments());
            }

            if(relevancyResult.getQuery().contentEquals(biofuelArticle2Query)) {
                assertArrayEquals(biofuelArticle2Results, relevancyResult.getResults());
                assertEquals(biofuelArticleTotalRelevant, relevancyResult.getTotalRelevantDocuments());
            }
        }

    }


    @Test
    void getResults() {
        String topicIdentifier = "Green%20sea%20turtle";
        int [] relevancyResults ={1,1,1,1,1,1,1,0,0,0,0,0,0,0};

        assertArrayEquals(relevancyResults,eval.getResults(topicIdentifier));


    }

}
