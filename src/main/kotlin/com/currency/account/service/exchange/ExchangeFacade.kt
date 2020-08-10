package com.currency.account.service.exchange

import com.currency.account.service.pesel.Pesel

class ExchangeFacade(private val exchangeService: ExchangeService){
  fun exchange(command: ExchangeCommand, pesel: Pesel) =  exchangeService.exchange(command, pesel)


}