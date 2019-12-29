package com.dakers.poker.shared.rules

import com.dakers.poker.shared.round.Round

abstract class RoundRules {
  def rounds: Seq[Round]
}
