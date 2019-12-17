package com.dakers.poker.shared

import org.scalatest.{FlatSpec, Matchers}

class ChipsTest extends FlatSpec with Matchers {

  "A stack" should "be an instance of Chips with the ability to add and remove chips from it" in {
    val stack = Stack(1)
    stack shouldBe a[Chips]
    stack shouldBe a[Add]
    stack shouldBe a[Remove]
  }

  "A pot" should "be an instance of Chips with the ability to add chips, but not remove chips from it" in {
    val pot = Pot(1)
    pot shouldBe a[Chips]
    pot shouldBe a[Add]
    pot should not be a[Remove]
  }

  val initialStackSize = 100.1
  val chipsToAddToStack = 15.2
  val finalStackSizeAdd: Double = initialStackSize + chipsToAddToStack

  "Adding " + chipsToAddToStack + " to a stack that had " + initialStackSize + " chips in it " should " yield a stack with " + finalStackSizeAdd + " chips in it" in {
    val origStack = Stack(initialStackSize)
    val finalStack = origStack.add(chipsToAddToStack)
    finalStack.amt shouldBe finalStackSizeAdd
  }

  val chipsToRem = 99.9
  val finalStackSizeRem: Double = initialStackSize - chipsToRem
  "Removing " + chipsToRem + " chips from a stack that had " + initialStackSize + " chips in it " should " yield a stack with " + finalStackSizeRem + " chips in it" in {
    val origStack = Stack(initialStackSize)
    val finalStack = origStack.rem(chipsToRem)
    finalStack.amt shouldBe finalStackSizeRem
  }

  val initialPotSize = 11.1
  val chipsToAddToPot = 15.2
  val finalPotSizeAdd = initialPotSize + chipsToAddToPot

  "Adding " + chipsToAddToPot + " to a pot that had " + initialPotSize + " chips in it " should " yield a pot with " + finalPotSizeAdd + " chips in it" in {
    val origPot = Stack(initialPotSize)
    val finalPot = origPot.add(chipsToAddToPot)
    finalPot.amt shouldBe finalPotSizeAdd
  }

  val chipsToRemoveFromPot = 15.2
  val finalPotSizeRem = initialPotSize - chipsToRemoveFromPot

  "Removing " + chipsToRemoveFromPot + " to a pot that had " + initialPotSize + " chips in it " should " yield a pot with " + finalPotSizeRem + " chips in it" in {
    val origPot = Stack(initialPotSize)
    val finalPot = origPot.add(chipsToAddToPot)
    finalPot.amt shouldBe finalPotSizeAdd
  }

}
