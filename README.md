# TrecCar

Evaluation Framework for the Text Retrieval project.

Steps to compile, intall & run the framework on Linux enviroment:

1. Clone the project from github: git clone https://github.com/ReazulHasan/TrecCar.git
2. Go inside the TrecCar directory: cd TrecCar
3. Invoke Maven to build the project: mvn package
4. Go inside the target directory: cd target
5. Run the program with two parameters containing the file name with relative paths for ground truth & result files: java -jar
TrecCarEvaluation-1.0-SNAPSHOT-jar-with-dependencies.jar <ground-truth-qrelfile> <results-qrelfile>
A sample runcould be: java -jar TrecCarEvaluation-1.0-SNAPSHOT-jar-with-dependencies.jar ../spritzer-v1.4/spritzer.cbor.article.qrels ../results/results.spritzer.cbor.article.qrels.1.test

The result will be shown on the terminal as a table of different evaluation measures:

-----------------------------------------------------------------
Official TREC Metrics: Across All Queries
-----------------------------------------------------------------
Description                       Trec Measure     Value
-----------                       ------------     -----
Number of queries                 num_q            1
Number of retrieved documents     num_ret          14
Relevant documents in corpus      num_rel          53
Relevant documents retrieved      num_rel_ret      7
Mean Average Precision            map              0.1321
R-Precision                       Rprec            0.1321
Mean Reciprocal Rank (MRR):       recip_rank       1.0000
Precision@5                       P_5              1.0000
Precision@10                      P_10             0.7000
Precision@15                      P_15             0.4667
Precision@20                      P_20             0.3500
Precision@30                      P_30             0.2333
Precision@100                     P_100            0.0700
Precision@200                     P_200            0.0350
Precision@500                     P_500            0.0140
Precision@1000                    P_1000           0.0070

Additional:
-----------------------------------------------------------------
Balanced F1:                                       0.2090


