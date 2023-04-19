package com.lab.svHin
import scala.math._

class SvHin {

    val embeddedMat = new EmbeddingMatrix()
    def cos(x: Double): Double = cos(x)

    def cosine(
      mat: Array[Array[Double]]
    ): Array[Array[Double]] = {
        for(i <- 0 to mat.length) {
          for(j <- 0 to mat(i).length) {
            mat(i)(j) = cos(mat(i)(j))
          }
        }
        mat
    }

    def multimat(
      prod: Array[Array[Double]],
      cons: Array[Array[Double]]
    ): Array[Array[Double]] = {
       // u * v
      Array[Array[Double]]()
    }

    def hin(
      prod: Array[Array[Double]],
      cons: Array[Array[Double]]
    ): Array[Array[Double]] = {
      var result = multimat(prod = prod, cons = cons)
      result = cosine(result)
      result
    }

}


