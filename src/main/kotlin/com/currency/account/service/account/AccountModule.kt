package com.currency.account.service.account

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
class AccountModule {

  @Bean
  fun accountFacade(accountRepository: AccountRepository, clock: Clock): AccountFacade {
    return AccountFacade(AccountCreator(accountRepository, LegalAgeValidator(clock)))
  }

}