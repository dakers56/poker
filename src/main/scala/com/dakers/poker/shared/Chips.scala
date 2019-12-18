package com.dakers.poker.shared

import scala.util.Try

/**
 * Represents a pile of chips. Used for a player's stack and the hand's pot.
 */
class Chips(val amt: Double)

object Chips {
  def apply(amt: Double): Chips = new Chips(amt)
}

/**
 * Represents a player's stack. A stack needs to have chips added and removed from it as players bet and win pots.
 */
class Stack(amt: Double) extends Chips(amt) with Add[Stack] with Remove[Stack] {
  override def add(toAdd: Double): Try[Stack] = {
    if (!canAdd(toAdd)) {
      throw new RuntimeException("Cannot add " + toAdd + " chips. Must add a positive number of chips.")
    }
    Try(Stack(amt + toAdd))
  }

  override def rem(toRem: Double): Try[Stack] = {
    if (!canRem(toRem)) {
      throw new RuntimeException("Cannot remove " + toRem + " chips. Must remove a positive number of chips.")
    }
    Try(Stack(amt - toRem))
  }
}

object Stack {
  def apply(amt: Double): Stack = new Stack(amt)
}

/**
 * Represents the pot in a game. Never needs to remove chips because chips will only be added when bets are complete.
 * Otherwise, the bet is illegal.
 */
class Pot(amt: Double) extends Chips(amt) with Add[Pot] {
  override def add(toAdd: Double): Try[Pot] = {
    if (!canAdd(toAdd)) {
      throw new RuntimeException("Cannot add " + toAdd + " chips. Must add a positive number of chips.")
    }
    Try(Pot(amt + toAdd))
  }
}

object Pot {
  def apply(amt: Double): Pot = new Pot(amt)
}

/**
 * Adds given amount to a [[Chips]] instance.
 */
trait Add[T <: Chips] {
  def add(toAdd: Double): Try[T]

  def canAdd(toAdd: Double) = toAdd > 0
}

/**
 * Removes given amount from a [[Chips]] instance.
 */
trait Remove[T <: Chips] {
  def rem(toRem: Double): Try[T]

  def canRem(toAdd: Double) = toAdd > 0

}


