package com.currency.account.service.nbp

import com.currency.account.service.account.Currency
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.math.BigDecimal

interface NbpClient {
  fun getExchangeRateAgainstZloty(currency: Currency): BigDecimal
}

class NbpClientImpl(private val nbpFeignClient: NbpFeignClient) : NbpClient {

  override fun getExchangeRateAgainstZloty(currency: Currency) =
          nbpFeignClient.getExchangeRateAgainstZloty(currency)
                  .rates
                  .first()
                  .mid


}

@FeignClient(name = "NBP", url = "http://api.nbp.pl/")
interface NbpFeignClient {

  @GetMapping("api/exchangerates/rates/a/{currency}/")
  fun getExchangeRateAgainstZloty(@PathVariable("currency") currency: Currency): NBPRateResponse

}

data class NBPRateResponse(val rates: List<Rate>)
data class Rate(val mid: BigDecimal)

