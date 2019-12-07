package com.dakers.poker.shared

/**
 * Base class for players of an arbitrary game. Players always have some sort of hand.
 */
abstract class Player {
  def id: String

  def hand: Hand
}

/**
 * Trait representing a player's starting hand.
 */
trait StartingHand {
  def startingHand: Hand
}