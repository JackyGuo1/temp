package com.zetdata.hero.trial
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.feature.HashingTF
import org.apache.spark.rdd.RDD
import scala.collection.mutable

class MyHashTf(override val  numFeatures: Int) extends HashingTF {
  
  def mytransform[D <: Iterable[_]](dataset: RDD[D]): RDD[Vector] = {
    dataset.map(this.mytransform)
  }
  
 def mytransform(document: Iterable[_]): Vector = {
    val termFrequencies = mutable.HashMap.empty[Int, Double]
    var id:Int = 0
    var cur = 0
    document.foreach { term =>
      val i = indexOf(term)
      cur = cur + 1
      if (cur ==0) id = term.toString.toInt
      termFrequencies.put(i, termFrequencies.getOrElse(i, 0.0) + 1.0)
    }
    Vectors.sparse(id, termFrequencies.toSeq)
  }
}
