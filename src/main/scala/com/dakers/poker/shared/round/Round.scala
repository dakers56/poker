package com.dakers.poker.shared.round

import com.dakers.cards.Card

/**
 * Defines a round of betting.
 */
abstract class Round(val name: String)

/**
 * Trait representing the cards that are part of a round of betting.
 */
trait Cards {
  def cards: Seq[Card]
}

/** Used for betting rounds like the flop where more cards go down.
 *
 * @param name name of the round -e.g, "flop", "third street"
 */
class RoundWithCards(name: String, val cards: Card*) extends Round(name) with Cards

/** Used for betting rounds like pre-flop where no cards go down.
 *
 * @param name name of the round -e.g, "Pre-Flop"
 */
class RoundWithoutCardsDefinition(name: String) extends Round(name)