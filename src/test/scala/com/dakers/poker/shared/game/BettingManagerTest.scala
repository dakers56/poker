package com.dakers.poker.shared

import com.dakers.poker.shared.action.Bet
import com.dakers.poker.shared.game.{BetNotAllowedException, BettingManager, BettingResult}
import com.dakers.poker.shared.rules.{ActionRules, BetFailureReason, BettingContext}
import org.mockito.Mockito._
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.scalatestplus.mockito.MockitoSugar

import scala.util.Failure

class BettingManagerTest extends FlatSpec with Matchers with BeforeAndAfter with MockitoSugar {

  "A BettingManager" should "return a failure on an invalid bet" in {
    val failMsg = "Bet failed."
    val betFailureReason = mock[BetFailureReason]
    when(betFailureReason.failureReason).thenReturn(failMsg)

    val rules = mock[ActionRules]
    val badBet = mock[Bet]
    implicit val bettingContext = mock[BettingContext]
    when(rules.failureReason(badBet)).thenReturn(Some(betFailureReason))


    val bettingManager = BettingManager(rules)
    val result = bettingManager.bet(badBet)

    result shouldBe a[Failure[BettingResult]]

    the[BetNotAllowedException] thrownBy result.get should have message failMsg
  }

  "A BettingManager" should "return a result with the stack set to the original amount less the bet, and the pot set to the original amount plus the bet" in {
    val origStackAmt = 100
    val origPotAmt = 200

    val origStack = Stack(origStackAmt)
    val origPot = Pot(origPotAmt)

    val betAmt = 50
    val bet = Bet(betAmt)

    val rules = mock[ActionRules]
    implicit val bettingContext = mock[BettingContext]
    when(bettingContext.stack).thenReturn(origStack)
    when(bettingContext.pot).thenReturn(origPot)
    when(rules.failureReason(bet)).thenReturn(Option.empty)

    val bettingManager = BettingManager(rules)

    val result = bettingManager.bet(bet).get

    val actualFinalPotAmt = result.pot.amt
    val expectedFinalPotAmt = origPotAmt + betAmt
    actualFinalPotAmt shouldBe expectedFinalPotAmt

    val actualFinalStackAmt = result.stack.amt
    val expectedFinalStackAmt = origStackAmt - betAmt
    actualFinalStackAmt shouldBe expectedFinalStackAmt
  }

  "A BettingManager" should "capture any exception thrown by the stack" in {
    val badStack = mock[Stack]
    val goodPot = mock[Pot]

    implicit val bettingContext = mock[BettingContext]
    when(bettingContext.stack).thenReturn(badStack)
    when(bettingContext.pot).thenReturn(goodPot)

    val bet = Bet(1)

    val rules = mock[ActionRules]
    when(rules.failureReason(bet)).thenReturn(Option.empty)

    val bettingManager = BettingManager(rules)
    val result = bettingManager.bet(bet)

    result shouldBe a[Failure[BettingResult]]

    a[RuntimeException] shouldBe thrownBy(result.get)
  }

  "A BettingManager" should "capture any exception thrown by the pot" in {
    val goodStack = mock[Stack]
    val badPot = mock[Pot]

    implicit val bettingContext = mock[BettingContext]
    when(bettingContext.stack).thenReturn(goodStack)
    when(bettingContext.pot).thenReturn(badPot)

    val bet = Bet(1)

    val rules = mock[ActionRules]
    when(rules.failureReason(bet)).thenReturn(Option.empty)

    val bettingManager = BettingManager(rules)
    val result = bettingManager.bet(bet)

    result shouldBe a[Failure[BettingResult]]

    a[RuntimeException] shouldBe thrownBy(result.get)
  }

}
