package com.dakers.poker.shared

trait Action

/**
 * Represents a bet made by a player.
 *
 * @param amount size of bet
 */
class Bet(amount: Int) extends Action

/**
 * Represents a check by a player. Object because a "check" has no state.
 */
class Check extends Action

/**
 * Represents a fold by a player. Object because a "fold" has no state.
 */
class Fold extends Action
