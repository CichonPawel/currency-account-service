package com.currency.account.service.account

import com.currency.account.service.exchange.ExchangeExternalCommand
import com.currency.account.service.pesel.Pesel

class AccountFacade(private val accountCreator: AccountCreator,
                    private val accountFinder: AccountFinder,
                    private val balanceChanger: BalanceChanger) {
  fun createAccount(command: CreateAccountCommand): Account = accountCreator.create(command)
  fun find(pesel: Pesel): Account = accountFinder.find(pesel)
  fun exchange(command: ExchangeExternalCommand) = balanceChanger.changeBalance(command)
}