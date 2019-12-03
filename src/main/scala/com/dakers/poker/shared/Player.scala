package com.dakers.poker.shared

/**
 * Base trait for players of an arbitrary game. Only includes an identifier because different implementations may have different
 * needs depending on the context which can be filled be stacking other traits in this file.
 */
trait Player {
  val name: String
}

/**
 * Trait representing the number of chips that a player has.
 */
trait Stack {
  def stack: Int
}

/**
 * Trait representing a player's starting hand.
 */
trait StartingHand {
  def startingHand: Hand
}