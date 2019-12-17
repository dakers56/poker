package com.dakers.poker.shared

import com.dakers.poker.shared.Pot.Pot
import com.dakers.poker.shared.Stack.Stack

abstract class Game {
  def players: Seq[Player]
}


//trait Betting {
//  def pot: Pot
//
//  def act(player: PlayerWithStack, action: Action): Option[(Stack, Pot)] = {
//    isAllowed(player, action) match {
//      case true =>
//        action match {
//          case Bet(amt, _, _) => Some((player.stack.add(amt).asInstanceOf[Stack], pot.add(amt).asInstanceOf[Pot]))
//        }
//      case false => None
//    }
//  }
//
//  def isAllowed(player: PlayerWithStack, action: Action): Boolean
//}