package com.currency.account.service.account

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
class AccountModule {

  @Bean
  fun accountFacade(accountRepository: AccountRepository, clock: Clock): AccountFacade {
    val accountCreator = AccountCreator(accountRepository, LegalAgeValidator(clock))
    val accountFinder = AccountFinder(accountRepository)
    val balanceChanger = BalanceChanger(accountRepository)
    return AccountFacade(accountCreator, accountFinder, balanceChanger)
  }

  @Bean
  fun accountRepository(): AccountRepository = InMemoryAccountRepository()

}