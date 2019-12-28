package com.dakers.poker.shared.rules

import com.dakers.poker.shared.{Action, Bet}

/**
 * Represents the rules governing subclasses of [[Action]]. Determines whether a given [[Action]] is allowed, and if not,
 * contains the reason why it is impermissible.
 */
abstract class ActionRules {
  /**
   * Evaluates an [[Action]] to determine if it is allowed.
   *
   * @param action The [[Action]] to evaluate
   * @return [[None]] if the bet is allowed, or if the bet is not allowed, an [[Option]] containing the reason why it failed.
   */
  def failureReason(action: Action)(implicit actionContext: ActionContext): Option[BetFailureReason]
}

/**
 * Encapsulates the reason for a bet failing.
 *
 * @param bet           the failed bet
 * @param failureReason string describing why the bet failed. For instance, a player can not make a bet which is larger than the size of their stack.
 */
class BetFailureReason(bet: Bet, val failureReason: String)
