package com.dakers.poker.shared.player

import com.dakers.poker.shared.hand.Hand
import com.dakers.poker.shared.misc.Stack

/**
 * Base class for players of an arbitrary game. Players always have some sort of hand.
 */
abstract class Player {
  def id: String

  def hand: Hand
}

trait PlayerWithStack extends Player {
  def stack: Stack
}

/**
 * Trait representing a player's starting hand.
 */
trait StartingHand {
  def startingHand: Hand
}