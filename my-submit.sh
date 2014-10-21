SPARK_HOME=/home/ansibler/work/spark/spark-1.1.0-bin-cdh4
$SPARK_HOME/bin/spark-submit  \
  --class "com.zetdata.hero.trial.SimpleApp" \
 --master local[4] \
  target/scala-2.10/spark_word_segement.jar 
# --master spark://10.10.0.114:7077  \
 
