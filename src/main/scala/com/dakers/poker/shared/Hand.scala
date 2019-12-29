package com.dakers.poker.shared

import com.dakers.cards.Card

/** Base class for hands. */
abstract class Hand {
  def cards: Iterable[Card]
}

/**
 * All cards of a "complete" hand are known, so it should implement [[Hand.cards()]] as a val.
 *
 * @param cards The cards in this starting hand.
 */
case class CompleteHand(cards: Set[Card]) extends Hand

