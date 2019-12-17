package com.dakers.poker.shared

trait Action

///**
// * Represents a bet made by a player.
// *
// * @param amount size of bet
// */
//case class Bet(amount: Double, implicit val player: PlayerWithStack, implicit val game: Game) extends Action
//
///**
// * Represents a check by a player. Object because a "check" has no state.
// */
//case class Check(implicit val player: PlayerWithStack, implicit val game: Game) extends Action
//
///**
// * Represents a fold by a player. Object because a "fold" has no state.
// */
//case class Fold(implicit val player: PlayerWithStack, implicit val game: Game) extends Action


trait BettingActions {
  this: Player =>
  def bet(amount: Double)(implicit game: Game): (PlayerWithStack, Game)

  def check(implicit game: Game): (PlayerWithStack, Game)

  def fold(implicit game: Game): (PlayerWithStack, Game)
}