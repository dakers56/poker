package com.dakers.poker.shared

import com.dakers.cards.Card

/**
 * Defines a round of betting.
 */
trait Round {
  val name: String
}

/**
 * Trait representing the cards that are part of a round of betting.
 */
trait Cards {
  def cards(): Seq[Card]
}

/** Used for betting rounds like the flop where more cards go down.
 *
 * @param name name of the round -e.g, "flop", "third street"
 */
class RoundWithCards(val name: String, val cards: Card*) extends Round with Cards

/** Used for betting rounds like pre-flop where no cards go down.
 *
 * @param name name of the round -e.g, "Pre-Flop"
 */
class RoundWithoutCardsDefinition(val name: String) extends Round