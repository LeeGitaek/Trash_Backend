package com.lab.gtCluster.knn

import scala.collection.mutable.PriorityQueue

case class DataPoint(features: Array[Double], label: Int)

class KNNModule(k: Int, data: Array[DataPoint]) {

  def predict(features: Array[Double]): Int = {
    val heap = new PriorityQueue[(Double, Int)]()(Ordering.by(-_._1))
    for (i <- data.indices) {
      val dist = euclideanDistance(features, data(i).features)
      // features =>it needs to predict data, data(i).features => base data
      // dist => euclideanDistance
      if(heap.size < k) {
        heap.enqueue((dist, data(i).label))
      } else if (heap.head._1 > dist) {
        heap.dequeue()
        heap.enqueue((dist, data(i).label))
      }
    }

    val counts = heap.groupBy(_._2).mapValues(_.size)
    counts.maxBy(_._2)._1
  }

  def euclideanDistance(x: Array[Double], y: Array[Double]): Double = {
      math.sqrt(x.zip(y).map { case (a, b) => math.pow(a-b, 2)}.sum)
  }
}
