# TrecCar Evaluation Framework for the Text Retrieval project.

## Steps to compile, intall & run the framework on Linux enviroment:

1. Clone the project by running the command:
```
    github: git clone https://github.com/ReazulHasan/TrecCar.git
```
2. Go inside the TrecCar directory: 
```
    cd TrecCar
```
3. Invoke Maven to build the project: 
``` 
    mvn package
```
4. Go inside the bin directory: 
```
    cd bin
```
5. Run the program with two parameters containing the file name with relative paths for ground truth & result files: 
```
    eval <qrels file> <trec formatted results file>
```
    A sample run could be: 
```
    eval ../data/spritzer.cbor.article.qrels ../results/results.spritzer.cbor.article.qrels.1.test
```
## Additional Documentation
Additional documentation of the code is available.  It is created by running the following command after mvn package from the TrecCar directory

    mvn javadoc:javadoc

After running the maven command to create the code documentation.  The javadoc documentation for the project is located at the following location
    **/TrecCar/target/site/index.html** 


## Troubleshooting

If the eval bash shell script does not execute the program.  The java command to execute the program is
```
    java -jar TrecCarEvaluation-1.0-SNAPSHOT-jar-with-dependencies.jar <qrelfile> <results file>
```
For example,
```
    java -jar TrecCarEvaluation-1.0-SNAPSHOT-jar-with-dependencies.jar spritzer.cbor.article.qrels results.spritzer.cbor.article.qrels.1.test
```

## Output for test200-mock1.run

```
-----------------------------------------------------------------
Results for:
Ground Truth File:           ../data/all.test200.cbor.hierarchical.qrels
Trec Formatted Results File: ../results/test200-mock1.run
-----------------------------------------------------------------

Official TREC Metrics: Across All Queries
-----------------------------------------------------------------
Description                       Trec Measure     Value
-----------                       ------------     -----
Number of queries                 num_q            2254
Number of retrieved documents     num_ret          85418
Relevant documents in corpus      num_rel          5336
Relevant documents retrieved      num_rel_ret      5129
Mean Average Precision            map              0.1645
R-Precision                       Rprec            0.0739
Mean Reciprocal Rank (MRR):       recip_rank       0.2050
Precision@5                       P_5              0.0713
Precision@10                      P_10             0.0723
Precision@15                      P_15             0.0734
Precision@20                      P_20             0.0703
Precision@30                      P_30             0.0591
Precision@100                     P_100            0.0226
Precision@200                     P_200            0.0114
Precision@500                     P_500            0.0046
Precision@1000                    P_1000           0.0023

Additional:
-----------------------------------------------------------------
Balanced F1:                                       0.1310
