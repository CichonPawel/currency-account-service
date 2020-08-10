package com.currency.account.service.account

import com.currency.account.service.pesel.Pesel
import java.lang.RuntimeException
import java.util.concurrent.ConcurrentHashMap

interface AccountRepository {
  fun createAccount(account: Account): Account
  fun getAccount(pesel: Pesel): Account?
  fun changeBalance(pesel: Pesel, moneys: List<Money>)
}

class InMemoryAccountRepository : AccountRepository {

  private val accounts = ConcurrentHashMap<Pesel, Account>()

  override fun createAccount(account: Account): Account {
    accounts[account.pesel] = account
    return account
  }

  override fun getAccount(pesel: Pesel): Account? = accounts[pesel]

  override fun changeBalance(pesel: Pesel, moneys: List<Money>) {
    val account = getAccount(pesel) ?: throw AccountNotExistException(pesel)
    val balancedSubAccounts = moneys.map { changeBalance(it, account) }
    accounts[account.pesel] = account.copyWithSubAccountsWithChangedBalance(balancedSubAccounts.toSet())
  }

  private fun changeBalance(money: Money, account: Account): SubAccount {
    return find(account, money.currency)
            ?.let { SubAccount(Money(money.currency, it.money.value.add(money.value))) }
            ?: SubAccount(Money(money.currency, money.value))
  }

  private fun find(account: Account, currency: Currency) =
          account.subAccounts.find { it.money.currency == currency }

  private fun Account.copyWithSubAccountsWithChangedBalance(changedBalanceSubAccounts: Set<SubAccount>): Account{
    return copy(subAccounts =     this.subAccounts
            .filter { !changedBalanceSubAccounts
                    .map { changedBalanceSubAccount -> changedBalanceSubAccount.money.currency }
                    .contains(it.money.currency)
            }
            .plus(changedBalanceSubAccounts).toSet())
  }

}

class AccountNotExistException(pesel: Pesel) : RuntimeException("Account with ${pesel.value} not exist")
