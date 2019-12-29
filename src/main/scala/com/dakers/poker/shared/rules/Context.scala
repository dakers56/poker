package com.dakers.poker.shared.rules

import com.dakers.poker.shared.misc.{Pot, Stack}

/**
 * Represents the additional information needed to validate and execute an action.
 */
abstract class ActionContext

/**
 * Represents the information needed specifically for betting. See [[ActionContext]].
 *
 * @param stack the player's stack
 * @param pot   the pot for the current hand
 */
abstract class BettingContext(val stack: Stack, val pot: Pot) extends ActionContext