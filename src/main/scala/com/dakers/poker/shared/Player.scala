package com.dakers.poker.shared

/**
 * Base class for players of an arbitrary game. Players always have some sort of hand.
 */
abstract class Player {
  def hand: Hand

  def id: String
}

/**
 * Defines the possible actions a player can take when it is their turn to bet.
 * Does not modify the player itself because the rules of the game may not permit the action.
 * Hence the player will "propose" one of these actions by invoking one of the methods, and the game will "validate" it.
 */
trait BettingActions {
  def bet(amount: Int) = Bet(amount)

  def check() = Check

  def fold() = Fold
}

/**
 * Represents a bet made by a player.
 *
 * @param amount size of bet
 */
case class Bet(amount: Int)

/**
 * Represents a check by a player. Object because a "check" has no state.
 */
case object Check

/**
 * Represents a fold by a player. Object because a "fold" has no state.
 */
case object Fold


/**
 * Trait representing a player's starting hand.
 */
trait StartingHand {
  def startingHand: Hand
}