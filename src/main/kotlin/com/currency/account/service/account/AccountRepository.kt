package com.currency.account.service.account

import java.util.concurrent.ConcurrentHashMap

interface AccountRepository {
  fun createAccount(account: Account): Account
  fun getAccount(pesel: Pesel): Account?
}

class InMemoryAccountRepository : AccountRepository {

  private val accounts = ConcurrentHashMap<Pesel, Account>()

  override fun createAccount(account: Account): Account {
    accounts[account.pesel] = account
    return account
  }

  override fun getAccount(pesel: Pesel): Account? = accounts[pesel]


}
