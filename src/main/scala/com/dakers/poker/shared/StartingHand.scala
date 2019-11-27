package com.dakers.poker.shared

import com.dakers.cards.Card

abstract class StartingHand(nCards: Int)

sealed case class HoleCards(cards: Card*)

sealed case class UpCards(cards: Card*)