package com.dakers.poker.shared

/**
 * Defines a round of betting.
 *
 * @param name Name of the round - e.g "Flop", "Third Street"
 */
abstract class RoundDefinition(val name: String)

/** Used for betting rounds like the flop where more cards go down.
 *
 * @param name   name of the round -e.g, "flop", "third street"
 * @param nCards number of cards that are added as part of the round
 */
class RoundWithCardsDefinition(name: String, val nCards: Int) extends RoundDefinition(name)

/** Used for betting rounds like pre-flop where no cards go down.
 *
 * @param name name of the round -e.g, "Pre-Flop"
 */
class RoundWithoutCardsDefinition(name: String) extends RoundDefinition(name)