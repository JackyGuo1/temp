package com.zetdata.hero.trial
/* SimpleApp.scala */
import scala.collection.JavaConverters._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.mllib.feature.HashingTF
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.mllib.feature.IDF
import org.wltea.analyzer.core.IKSegmenter
import org.wltea.analyzer.core.Lexeme
import java.util.LinkedList

object SimpleApp {
  def wordSegment(p :String):(Int,Seq[String]) = {
       var id_descriptions = p.split(",")
       val id =id_descriptions(0)
       var tokens = new LinkedList[String]()
       var ikSegmenter = new IKSegmenter(new java.io.StringReader(p),true)
       var lexeme = ikSegmenter.next();
           while(lexeme!=null){
                        tokens.add(lexeme.getLexemeText());
                        lexeme = ikSegmenter.next();
                }
                (id.toInt,tokens.asScala)
  }

  def main(args: Array[String]) {
   // val logFile = "/home/ansibler/work/spark/all.txt" // Should be some file on your system
   // val logFile = "hdfs://10.10.0.114/tmp/all.txt"
    val rawFile = "/home/ansibler/jiaqi-sbt/simple/testData.txt"
    val jdFile = "/home/ansibler/jiaqi-sbt/simple/for-tfidf/jd.txt"
    val rsFile = "/home/ansibler/jiaqi-sbt/simple/for-tfidf/rs.txt"
    val conf = new SparkConf().setAppName("CosineMatch Application")
    val sc = new SparkContext(conf)


    val documents: RDD[(Int,Seq[String])] = sc.textFile(rsFile).map(wordSegment)
    documents.coalesce(1, false).saveAsTextFile("./wordSegmentFile")
    println(documents.partitions)
/*    val hashingTF = new MyHashTf(1<<20)
    val tf: RDD[Vector] = hashingTF.mytransform(documents)
    tf.saveAsTextFile("./tf")

    tf.cache()
    val idf = new IDF().fit(tf)
    val tfidf: RDD[Vector] = idf.transform(tf)
    val tfidf_results: Int = tfidf.map(v => v.size).reduce((a,b) => a + b)
    println("%s".format(tfidf.first()))
    println("total %s".format(tfidf.count()))
    println("vector length %s".format(tfidf.first().size))
    println("%s".format(tfidf_results))
    //documents.saveAsTextFile("hdfs://10.10.0.114/tmp/word_segment.txt")
    tfidf.saveAsTextFile("hdfs://10.10.0.114/tmp/tfidf/")
*/
  }
}

