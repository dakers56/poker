package com.dakers.poker.shared.rules

abstract class Round {
  def name: String

  def isComplete(): Boolean
}
