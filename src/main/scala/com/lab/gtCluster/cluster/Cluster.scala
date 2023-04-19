package com.lab.gtCluster.cluster

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class Point(x: Double, y: Double)

class KMeansCluster(k: Int, points: Array[Point]) {

  val MAX_ITERATIONS = 200
  val centroids = ArrayBuffer[Point]()
  val clusters = Array.ofDim[ArrayBuffer[Point]](k)

  for (i <- 0 until k) {
    clusters(i) = ArrayBuffer[Point]()
    centroids += randomPoint()
  }

  def run(): Array[Point] = {
    var iterations = 0
    var oldCentroids = ArrayBuffer[Point]()

    while (iterations < MAX_ITERATIONS && !converged(oldCentroids, centroids)) {
      iterations += 1
      oldCentroids = centroids.clone()

      clusters.foreach(_.clear())
      points.foreach { p =>
        val centroid = centroids.minBy(distance(_, p))
        clusters(centroids.indexOf(centroid)) += p
      }

      centroids.indices.foreach(i => {
        val cluster = clusters(i)
        if (cluster.nonEmpty) {
          centroids(i) = cluster.reduce((p1, p2) => Point(p1.x + p2.x, p1.y + p2.y))
          centroids(i) = Point(centroids(i).x / cluster.size, centroids(i).y / cluster.size)
        }
      })
    }
    centroids.toArray
  }

  private def converged(oldCentroids: ArrayBuffer[Point], newCentroids: ArrayBuffer[Point]): Boolean = {
    oldCentroids.zip(newCentroids).forall {
      case (a, b) => distance(a, b) < 0.0001
    }
  }

  private def randomPoint(): Point = {
    val rnd = new Random()
    val x = rnd.nextDouble() * points.map(_.x).max
    val y = rnd.nextDouble() * points.map(_.y).max
    Point(x, y)
  }

  private def distance(p1: Point, p2: Point): Double = {
    math.sqrt(math.pow(p1.x - p2.x, 2) + math.pow(p1.y - p2.y, 2))
  }
}
