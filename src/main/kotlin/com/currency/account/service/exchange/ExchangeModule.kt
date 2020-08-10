package com.currency.account.service.exchange

import com.currency.account.service.account.AccountFacade
import com.currency.account.service.nbp.NbpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExchangeModule {

  @Bean
  fun exchangeRateFacade(nbpClient: NbpClient, accountFacade: AccountFacade) =
          ExchangeFacade(ExchangeService(accountFacade, ExchangeRateNBPService(nbpClient)))

}