package com.currency.account.service.nbp

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NbpModule {

  @Bean
  fun nbpClient(nbpFeignClient: NbpFeignClient): NbpClient = NbpClientImpl(nbpFeignClient)
}