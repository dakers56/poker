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



/**
 * Encapsulates the reason for a bet failing.
 *
 * @param bet           the failed bet
 * @param failureReason string describing why the bet failed. For instance, a player can not make a bet which is larger than the size of their stack.
 */
class BetFailureReason(bet: Bet, val failureReason: String)

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
   * A similar result would ensure if someone tried to bet less than the big blind in NLHE, or more than the pot in PLO.
   *
   * See [[BettingResult]] for more information.
   *
   * @param bet [[Bet]] to validate
   * @return [[scala.util.Success]] containing [[BettingResult]] if successful, otherwise [[scala.util.Failure]] wrapping the exception thrown
   */
  final def bet(bet: Bet): Try[BettingResult] = {
    Try(betHelper(bet))
  }

  /**
   * Helper method used so that a [[Try]] can wrap the exception thrown when the bet is not valid.
   *
   * @param bet [[Bet]] to be validated
   * @return See [[BettingResult]]
   */
  private def betHelper(bet: Bet): BettingResult = {
    val failureReason = rules.failureReason(bet)
    if (failureReason.isDefined) {
      throw new BetNotAllowedException(failureReason.get.failureReason)
    }
    val betAmt = bet.amt
    val newStack = bet.stack.rem(betAmt)
    val newPot = bet.pot.add(betAmt)
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