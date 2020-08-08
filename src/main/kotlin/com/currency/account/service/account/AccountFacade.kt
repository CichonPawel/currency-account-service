package com.currency.account.service.account

class AccountFacade(private val accountCreator: AccountCreator,
                    private val accountFinder: AccountFinder) {
  fun createAccount(command: CreateAccountCommand): Account = accountCreator.create(command)
  fun find(pesel: Pesel): Account = accountFinder.find(pesel)
}