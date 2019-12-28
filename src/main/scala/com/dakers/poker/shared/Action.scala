package com.dakers.poker.shared

import com.dakers.poker.shared.rules.ActionRules

import scala.util.Try

/**
 * Base trait for actions that a player may take, such as betting, checking or folding.
 * Using a marker trait allows for the API signatures to be simpler and more extensible.
 */
trait Action

/**
 * Represents a bet from a player.
 * A stack and pot are required because if the bet is allowed, then the stack and pot need to be updated. See [[BettingResult]]
 * for more.
 *
 * @param amt   the size of the bet
 * @param stack the [[Stack]] of the player making the bet
 * @param pot   the current [[Pot]] in the hand
 */
case class Bet(amt: Double, stack: Stack, pot: Pot) extends Action

/**
 * Represents a check by the player.
 */
case object Check extends Action

/**
 * Represents a fold by the player.
 */
case object Fold extends Action


