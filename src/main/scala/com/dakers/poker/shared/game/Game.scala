package com.dakers.poker.shared.game

import com.dakers.poker.shared.player.Player
import com.dakers.poker.shared.rules.{BettingContext, Rules}

/**
 * Base class for all poker games. Minimally, the rules of the game must be defined.
 */
abstract class Game(val rules: Rules)

/**
 * Represents the fact that in poker, players act in turn - i.e, there is always 1 active player. Additionally,completion of the turn
 * must be handled. For instance, this could include updating the active player's stack, the hand's pot,
 * and setting the active player to the next player to act
 *
 */
trait Turn {
  type T <: Game

  /**
   * @return the player whose turn it is
   */
  def activePlayer(): Player

  /**
   * Used when a player is done acting. The return type is [[T]] so that the implementation can be immutable.
   * If the implementation is mutable, then the return type can simply be ignored.
   *
   * @return instance of a subclass of [[Game]] where the current player's turn has been completed
   */
  def completeTurn(): T
}

/**
 * Enables betting in the game by providing a [[BettingManager]] to delegate to.
 * The actual delegation can be handled by the implementation. For instance, an implicit
 * conversion could be used, or callers could simply invoke the betting manager directly.
 *
 * The [[BettingContext]] is included because the betting manager needs it as a
 */
trait Betting {
  def bettingManager: BettingManager

  implicit def bettingContext(): BettingContext
}

/**
 * Trait that allows a game to have players.
 */
trait Players {
  def players: Iterable[Players]
}