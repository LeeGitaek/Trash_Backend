package com.lab

import com.lab.gtCluster.cluster.{KMeansCluster, Point}
import com.lab.gtCluster.knn.{DataPoint, KNNModule}
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}
import org.springframework.web.bind.annotation.GetMapping


@Configuration
@EnableAutoConfiguration
@ComponentScan
class AppConfig {
  println("[START] SCALA SERVER ON!")


  // Example usage
  val data = Array(
    DataPoint(Array(1, 2), 4),
    DataPoint(Array(2, 3), 1),
    DataPoint(Array(0, 4), 3),
    DataPoint(Array(1, 2), 4),
    DataPoint(Array(5, 2), 1),
    DataPoint(Array(6, 7), 3)
  )

  val knn = new KNNModule(3, data)
  val prediction = knn.predict(Array(1, 2.9))
  println(prediction) // Output: 1

  // Usage example:
  val points = Array(Point(1, 2),
                     Point(2, 3),
                     Point(0, 4),
                      Point(1, 9),
                      Point(2, 8))
  val k = 2
  val kMeans = new KMeansCluster(k, points)
  val centroids = kMeans.run()
  centroids.foreach(println)
  // Point(6.098698873086276,8.752697425965232)
  // Point(8.57011161046278,3.87661087065859)

//  Point(1.9633366708520272, 8.858739553882572)
//  Point(1.0618114725026189, 4.024616622058503)
}
