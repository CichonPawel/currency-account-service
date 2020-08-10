package com.currency.account.service.exchange

import com.currency.account.service.ValidationException
import com.currency.account.service.account.Account

class ExchangeValidator {

  companion object{
    fun valid(account: Account, command: ExchangeCommand) {
      if (command.sourceCurrency == command.targetCurrency) {
        throw ValidationException("source and target Currency must be different")
      }
      if (account.balanceCurrency(command.sourceCurrency) < command.value) {
        throw ValidationException("account not have enough source Currency")
      }
    }
  }

}



