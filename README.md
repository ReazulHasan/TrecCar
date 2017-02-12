# TrecCar

Evaluation Framework for the Text Retrieval project

Steps to run the framework on Linux enviroment:

1. Clone the project from github: git clone https://github.com/ReazulHasan/TrecCar.git
2. Go inside the TrecCar directory: cd TrecCar
3. Invoke Maven to build the project: mvn package
4. Go inside the target directory: cd target
5. Run the program with two parameters containing the file name with relative paths for ground truth & result files: java -jar
TrecCarEvaluation-1.0-SNAPSHOT-jar-with-dependencies.jar <ground-truth-qrelfile> <results-qrelfile>
A sample runcould be: java -jar TrecCarEvaluation-1.0-SNAPSHOT-jar-with-dependencies.jar ../spritzer-v1.4/spritzer.cbor.article.qrels ../results/results.spritzer.cbor.article.qrels.1.test
