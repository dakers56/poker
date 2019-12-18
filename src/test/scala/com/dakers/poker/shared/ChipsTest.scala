package com.dakers.poker.shared

import org.scalatest.{FlatSpec, Matchers}

class ChipsTest extends FlatSpec with Matchers {

  "A stack" should "be an instance of Chips with the ability to add and remove chips from it" in {
    val stack = Stack(1)
    stack shouldBe a[Chips]
    stack shouldBe a[Add[Stack]]
    stack shouldBe a[Remove[Stack]]
  }

  "A pot" should "be an instance of Chips with the ability to add chips, but not remove chips from it" in {
    val pot = Pot(1)
    pot shouldBe a[Chips]
    pot shouldBe a[Add[Stack]]
    pot should not be a[Remove[Stack]]
  }

  val initialStackSize = 100.1
  val chipsToAddToStack = 15.2
  val finalStackSizeAdd: Double = initialStackSize + chipsToAddToStack

  "Adding " + chipsToAddToStack + " to a stack that had " + initialStackSize + " chips in it " should " yield a stack with " + finalStackSizeAdd + " chips in it" in {
    val origStack = Stack(initialStackSize)
    val finalStack = origStack.add(chipsToAddToStack).get
    finalStack.amt shouldBe finalStackSizeAdd
  }

  val chipsToRem = 99.9
  val finalStackSizeRem: Double = initialStackSize - chipsToRem
  "Removing " + chipsToRem + " chips from a stack that had " + initialStackSize + " chips in it " should " yield a stack with " + finalStackSizeRem + " chips in it" in {
    val origStack = Stack(initialStackSize)
    val finalStack = origStack.rem(chipsToRem).get
    finalStack.amt shouldBe finalStackSizeRem
  }

  val initialPotSize = 11.1
  val chipsToAddToPot = 15.2
  val finalPotSizeAdd = initialPotSize + chipsToAddToPot

  "Adding " + chipsToAddToPot + " to a pot that had " + initialPotSize + " chips in it " should " yield a pot with " + finalPotSizeAdd + " chips in it" in {
    val origPot = Stack(initialPotSize)
    val finalPot = origPot.add(chipsToAddToPot).get
    finalPot.amt shouldBe finalPotSizeAdd
  }

  val chipsToRemoveFromPot = 15.2
  val finalPotSizeRem = initialPotSize - chipsToRemoveFromPot

  "Removing " + chipsToRemoveFromPot + " to a pot that had " + initialPotSize + " chips in it " should " yield a pot with " + finalPotSizeRem + " chips in it" in {
    val origPot = Stack(initialPotSize)
    val finalPot = origPot.add(chipsToAddToPot).get
    finalPot.amt shouldBe finalPotSizeAdd
  }

  def addErrorMessage(amt: Double) = "Cannot add " + amt + " chips. Must add a positive number of chips."

  "Adding a negative number of chips to a stack" should "generate an exception" in {
    val stack = Stack(1)
    val toAdd = -1.0
    the[RuntimeException] thrownBy stack.add(toAdd).get should have message addErrorMessage(toAdd)
  }

  "Adding a negative number to a pot" should "generate an exception" in {
    val pot = Pot(1)
    val toRem = -1.0
    the[RuntimeException] thrownBy pot.add(toRem).get should have message addErrorMessage(toRem)
  }

  def remErrorMessage(amt: Double) = "Cannot remove " + amt + " chips. Must remove a positive number of chips."

  "Removing a negative number of chips from a stack" should "generate an exception" in {
    val stack = Stack(1)
    val toRem = -1.0
    the[RuntimeException] thrownBy stack.rem(toRem).get should have message remErrorMessage(toRem)
  }

}
