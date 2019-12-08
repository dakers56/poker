package com.dakers.poker.shared

/**
 * Represents a pile of chips. Used for a player's stack and the hand's pot.
 */
class Chips(val amt: Double)

/**
 * Represents a player's stack. A stack needs to have chips added and removed from it as a player bets and wins pots.
 */
object Stack {
  type Stack = Chips with Add with Remove
}

/**
 * Represents the pot in a game. Never needs to remove chips because chips will only be added when bets are complete.
 * Otherwise the bet is illegal.
 */
object Pot {
  type Pot = Chips with Add
}

/**
 * Adds given amount to a [[Chips]] instance.
 */
sealed trait Add {
  this: Chips =>
  def add(toAdd: Double) = {
    toAdd match {
      case x if x > 0 => new Chips(amt + toAdd)
      case _ => throw new RuntimeException("Must add a positive number of chips.")
    }
  }
}

/**
 * Removes given amount from a [[Chips]] instance.
 */
sealed trait Remove {
  this: Chips =>

  def rem(toRem: Double) = {
    toRem match {
      case x if x > 0 => new Chips(amt - toRem)
      case _ => throw new RuntimeException("Must remove a positive number of chips.")
    }
  }
}


