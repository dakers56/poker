package com.dakers.poker.shared.misc

/**
 * Bridges the gap between actual games - where a "full" hand or round is germane - and simulations, which need to consider
 * possibilities for "missing" cards.
 *
 * For instance, in a game of Texas Hold 'Em, the flop always has three cards. But if you are simulating what occurs when
 * you have pocket aces and there is an Ace on the board during the flop, but the other two cards are unknown, then you
 * somehow need to record that there are two "missing" cards, and your simulation can consider the possible values that
 * those two blank cards could take on.
 *
 * The choice of an [[Int]] as opposed to a "blank" object or [[None]] is designed to give consumers the flexibility to deal
 * with the absent cards however they want. All they need to know is that some number of cards is missing.
 */
trait Incomplete {
  def missingCards: Int
}
