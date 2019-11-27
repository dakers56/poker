package com.dakers.poker.shared

import com.dakers.cards.Card._
import org.scalatest.{FlatSpec, Matchers}

class StartingHandTest extends FlatSpec with Matchers {

  "A one card hand of hole cards" should "have exactly one card in it" in {
    val card = Ace of Spades
    val hand = HoleCards(card)
    assert(hand.cards.length == 1)
  }

  "A two card hand of hole cards" should "have exactly two cards in it" in {
    val card1 = Two of Diamonds
    val card2 = Eight of Clubs
    val hand = HoleCards(card1, card2)
    assert(hand.cards.length == 2)
  }

  "A three card hand of hole cards" should "have exactly three cards in it" in {
    val card1 = Two of Diamonds
    val card2 = Eight of Clubs
    val card3 = King of Spades
    val hand = HoleCards(card1, card2, card3)
    assert(hand.cards.length == 3)
  }

  "A one card hand of up cards" should "have exactly one card in it" in {
    val card = Ace of Spades
    val hand = UpCards(card)
    assert(hand.cards.length == 1)
  }

  "A two card hand of up cards" should "have exactly two cards in it" in {
    val card1 = Two of Diamonds
    val card2 = Eight of Clubs
    val hand = UpCards(card1, card2)
    assert(hand.cards.length == 2)
  }

  "A three card hand of up cards" should "have exactly three cards in it" in {
    val card1 = Two of Diamonds
    val card2 = Eight of Clubs
    val card3 = King of Spades
    val hand = UpCards(card1, card2, card3)
    assert(hand.cards.length == 3)
  }
}
