package com.lab.Score

import com.lab.repository.DataProviderRepository

class Score extends DataProviderRepository {

  // 데이터 마다 가중치 weight score 를 주도록 한다.
  // score 를 이용해서 svhin 으로 matrix 를 만들어야한다.

  def reputationRankScore(): Double = {
    0.0
  }

  def careerRankScore(): Double = {
    0.0
  }

  def textRankScore(): Double = {
    0.0
  }

  def mentorConsultPurchaseRankScore(): Double = {
    0.0
  }

  def userDirectMessageRankScoreWeight(): Double = {
    0.0
  }

  def informationRankScoreWeight(): Double = {
    0.0
  }

  def hashTagRankScoreWeight(): Double = {
    0.0
  }

  def languageRankScoreWeight(): Double = {
    0.0
  }

  def likeWeight(): Double = {
    0.0
  }

  def existCertificatedMark(): Double = {
    0.0
  }

  def postWeight(): Double = {
    0.0
  }

  def replyCommentWeight(): Double = {
    0.0
  }

}
