package com.dakers.poker.shared

import com.dakers.cards.Card

/** Base class for starting hands. Each game should have exactly one subtype of this class representing a starting hand.
 */
abstract class StartingHand {
  /**
   * @return [[Set]] of the cards contained in this starting hand. [[Set]] is used because each card can only be dealt once.
   */
  def cards(): Set[Card]
}