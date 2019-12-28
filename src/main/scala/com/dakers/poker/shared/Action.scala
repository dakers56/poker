package com.dakers.poker.shared

/**
 * Base trait for actions that a player may take, such as betting, checking or folding.
 * Using a marker trait allows for the API signatures to be simpler and more extensible.
 */
trait Action

/**
 * Represents a bet from a player.
 *
 * @param amt the size of the bet
 */
case class Bet(amt: Double) extends Action

/**
 * Represents a check by the player.
 */
case object Check extends Action

/**
 * Represents a fold by the player.
 */
case object Fold extends Action


