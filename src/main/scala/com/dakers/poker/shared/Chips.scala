package com.dakers.poker.shared

/**
 * Represents a pile of chips. Used for a player's stack and the hand's pot.
 */
case class Chips(amt: Double)

/**
 * Represents a player's stack. A stack needs to have chips added and removed from it as players bet and win pots.
 */
object Stack {
  type Stack = Chips with Add with Remove

  def apply(amt: Double): Stack = new Chips(amt) with Add with Remove
}

/**
 * Represents the pot in a game. Never needs to remove chips because chips will only be added when bets are complete.
 * Otherwise, the bet is illegal.
 */
object Pot {
  type Pot = Chips with Add

  def apply(amt: Double): Pot = new Chips(amt) with Add
}

/**
 * Adds given amount to a [[Chips]] instance.
 */
sealed trait Add {
  this: Chips =>
  def add(toAdd: Double) = Chips(amt + toAdd)
}

/**
 * Removes given amount from a [[Chips]] instance.
 */
sealed trait Remove {
  this: Chips =>
  def rem(toRem: Double) = Chips(amt - toRem)
}


