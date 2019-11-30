package com.dakers.poker.shared

import com.dakers.cards.Card

/** Base class for hands. */
trait Hand {
  def cards(): Set[Card]
}

/**
 * Used to compute possible outcomes given that this hand may have more cards. For instance, could be used to represent
 * a starting hand in NLHE that has an Ace, but the other card is not known.
 */
trait IncompleteHand extends Hand {
  val totalSize: Int

}

/**
 * All cards of a "complete" starting hand are known, so it should implement [[Hand.cards()]] as a val.
 *
 * @param cards The cards in this starting hand.
 */
case class CompleteStartingHand(cards: Set[Card]) extends Hand

