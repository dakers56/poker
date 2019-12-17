package com.dakers.poker.shared

import com.dakers.poker.shared.Stack.Stack

/**
 * Base class for players of an arbitrary game. Players always have some sort of hand.
 */
abstract class Player {
  def id: String

  def hand: Hand
}

trait PlayerWithStack {
  def stack: Stack
}

/**
 * Trait representing a player's starting hand.
 */
trait StartingHand {
  def startingHand: Hand
}