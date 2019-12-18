package com.dakers.poker.shared

trait Action

case class Bet(amt: Double, stack: Stack, pot: Pot) extends Action

case object Check extends Action

case object Fold extends Action

abstract class ActionRules {
  def isAllowed(action: Action): Boolean
}

class BettingResult(stack: Stack, pot: Pot)

object BettingResult {}

class BetNotAllowedException(msg: String) extends RuntimeException(msg)

class BettingManager(rules: ActionRules) {
//  def bet(bet: Bet): BettingResult = {
//    if (!rules.isAllowed(bet)) {
//      throw new BetNotAllowedException("Bet " + bet + " was not allowed.")
//    }
//    val betAmt = bet.amt
//    val newStack = bet.stack.rem(betAmt)
//    val newPot = bet.pot.add(betAmt)
//    BettingResult(newStack.get, newPot.get)
//  }


}