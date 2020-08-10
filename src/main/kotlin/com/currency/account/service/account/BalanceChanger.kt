package com.currency.account.service.account

import com.currency.account.service.exchange.ExchangeExternalCommand

class BalanceChanger (private val repository: AccountRepository){

  fun changeBalance(command: ExchangeExternalCommand){
    repository.changeBalance(command.pesel, listOf(command.sourceMoney, command.targetMoney))
  }
}