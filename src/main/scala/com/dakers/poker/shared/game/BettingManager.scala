package com.dakers.poker.shared.game

import com.dakers.poker.shared.action.Bet
import com.dakers.poker.shared.rules.{ActionRules, BettingContext}
import com.dakers.poker.shared.{Pot, Stack}

import scala.util.Try

/**
 * Represents aspects of the game that change after a bet. Whenever a bet is allowed, the player's stack and the hand's pot need
 * to be updated.
 *
 * @param stack the player's stack after the bet is completed
 * @param pot   the hand's pot after the bet is completed
 */
class BettingResult(val stack: Stack, val pot: Pot)

object BettingResult {
  /**
   * Instantiates a new [[BettingResult]]
   *
   * @param stack the player's stack after the bet is completed
   * @param pot   the hand's pot after the bet is completed
   * @return [[BettingResult]] with stack and pot set to given values
   */
  def apply(stack: Stack, pot: Pot): BettingResult = new BettingResult(stack, pot)
}

/**
 * Exception thrown when a bet is not allowed.
 *
 * @param msg message for the exception
 */
class BetNotAllowedException(msg: String) extends RuntimeException(msg)

/**
 * Class that evaluates whether a bet is allowed, and if it is, returns a [[BettingResult]].
 * This class is needed because when a player attempts a bet, the bet's validity depends on (at least) the rules of the
 * game, the player's stack, and the pot, and placing the responsibility of evaluating the bet and returning the new state
 * of the game in any one of them would break the Single Responsibility Principle.
 *
 * @param rules [[ActionRules]] used to evaluate whether a bet is allowed.
 */
class BettingManager(rules: ActionRules) {
  /**
   * This method is used to validate the given bet, and if it is valid, to return the information needed to update the state of the
   * game appropriately.
   *
   * For instance, if someone attempts to bet more than is in their stack, an instance of [[scala.util.Failure]] would be returned.
   * A similar result would ensue if someone tried to bet less than the big blind in NLHE, or more than the pot in PLO.
   *
   * See [[BettingResult]] for more information.
   *
   * @param bet            [[Bet]] to validate
   * @param bettingContext contains information needed to validate the bet. See [[BettingContext]].
   * @return [[scala.util.Success]] containing [[BettingResult]] if successful, otherwise [[scala.util.Failure]] wrapping the exception thrown
   */
  final def bet(bet: Bet)(implicit bettingContext: BettingContext): Try[BettingResult] = {
    Try(betHelper(bet))
  }

  /**
   * Helper method used so that a [[Try]] can wrap the exception thrown when the bet is not valid.
   *
   * @param bet            [[Bet]] to be validated
   * @param bettingContext contains information needed to validate the bet. See [[BettingContext]].
   * @return See [[BettingResult]]
   */
  private def betHelper(bet: Bet)(implicit bettingContext: BettingContext): BettingResult = {
    val failureReason = rules.failureReason(bet)
    if (failureReason.isDefined) {
      throw new BetNotAllowedException(failureReason.get.failureReason)
    }
    val betAmt = bet.amt
    val newStack = bettingContext.stack.rem(betAmt)
    val newPot = bettingContext.pot.add(betAmt)
    BettingResult(newStack.get, newPot.get)
  }
}

object BettingManager {
  /**
   * Instantiates [[BettingManager]]
   *
   * @param rules [[ActionRules]] that will be used to validate bets
   * @return [[BettingManager]] that uses the given [[ActionRules]]
   */
  def apply(rules: ActionRules): BettingManager = new BettingManager(rules)
}
