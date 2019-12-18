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
class Stack(amt: Double) extends Chips(amt) with Add with Remove

object Stack {
  def apply(amt: Double): Stack = new Stack(amt)
}

/**
 * Represents the pot in a game. Never needs to remove chips because chips will only be added when bets are complete.
 * Otherwise, the bet is illegal.
 */
class Pot(amt: Double) extends Chips(amt) with Add

object Pot {
  def apply(amt: Double): Pot = new Pot(amt)
}

/**
 * Adds given amount to a [[Chips]] instance.
 */
trait Add {
  this: Chips =>
  def add(toAdd: Double): Try[Chips] = {
    if (toAdd < 0) {
      throw new RuntimeException("Cannot add " + toAdd + " chips. Must add a positive number of chips.")
    }
    Try(Chips(amt + toAdd))
  }
}

/**
 * Removes given amount from a [[Chips]] instance.
 */
trait Remove {
  this: Chips =>
  def rem(toRem: Double): Try[Chips] = {
    if (toRem < 0) {
      throw new RuntimeException("Cannot remove " + toRem + " chips. Must remove a positive number of chips.")
    }
    Try(Chips(amt - toRem))
  }
}


