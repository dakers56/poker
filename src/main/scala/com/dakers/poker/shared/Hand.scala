package com.dakers.poker.shared

import com.dakers.cards.Card

/** Base class for hands. */
trait Hand {
  def cards(): Set[Card]
}

/**
 * Used to compute possible outcomes given that this hand may have more cards. For instance, could be used to represent
 * a starting hand in NLHE that has an Ace, but the other card is not known.
 * The idea is that if the cards and the number of "missing" cards is known, then the possibilities can be "filled in".
 * By only specifying the number of missing cards in this trait, this trait is decoupled from consumers' implementation. If, for instance,
 * a singleton "blank card" were used, then consumers would have an additional, potentially unnecessary, dependency.
 */
trait Incomplete {
  def missingCards(): Int
}


/**
 * All cards of a "complete" hand are known, so it should implement [[Hand.cards()]] as a val.
 *
 * @param cards The cards in this starting hand.
 */
case class CompleteHand(cards: Set[Card]) extends Hand

