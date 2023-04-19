package com.lab.svHin

import com.lab.Score.Score
import com.lab.svHin.ProducerEmbedded.{embedBuilder, ranker}

case class ProducerEmb(producer: Array[Int])
case class ConsumerEmb(consumer: Array[Int])
case class Emb(matrix: Array[Array[Double]])

class EmbedScore {
  val ranker: Score = new Score()
  val featureCount: Int = 12

  def embedBuilder(user: Array[Int]): List[Double] = {
    var community = List.fill(12)(0.0)
    community :+ (ranker.postWeight(), ranker.likeWeight())
    community :+ (ranker.textRankScore(), ranker.careerRankScore())
    community :+ (ranker.replyCommentWeight(), ranker.existCertificatedMark())
    community :+ (ranker.hashTagRankScoreWeight(),ranker.informationRankScoreWeight())
    community :+ (ranker.languageRankScoreWeight(), ranker.mentorConsultPurchaseRankScore())
    community :+ (ranker.reputationRankScore(),ranker.userDirectMessageRankScoreWeight())
    community
  }
}

object ProducerEmbedded extends EmbedScore {
  def producerEmbMatrix(
    embed: ProducerEmb
  ): Emb = {
      var matrix: Array[Array[Double]] = Array.ofDim(embed.producer.length, featureCount)
      for(i <- 0 until embed.producer.length) {
          matrix(i) = embedBuilder(embed.producer).toArray
      }
      Emb(matrix = matrix)
  }
}

object ConsumerEmbedded extends EmbedScore {
  def consumerEmbMatrix(
      embed: ConsumerEmb
  ): Emb = {
    var matrix: Array[Array[Double]] = Array.ofDim(embed.consumer.length, featureCount)
    for (i <- 0 until embed.consumer.length) {
        matrix(i) = embedBuilder(embed.consumer).toArray
    }
    Emb(matrix = matrix)
  }
}

class EmbeddingMatrix {
  def consumer(): Emb = {
    ConsumerEmbedded
      .consumerEmbMatrix(
        ConsumerEmb(
          consumer = Array(1,2,3)
          )
      )
  }

  def producer(): Emb = {
    ProducerEmbedded
      .producerEmbMatrix(
        ProducerEmb(
          producer = Array(4,5,6)
        )
      )
  }

}
