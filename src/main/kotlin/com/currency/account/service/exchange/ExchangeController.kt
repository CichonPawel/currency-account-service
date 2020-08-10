package com.currency.account.service.exchange

import com.currency.account.service.pesel.Pesel
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping
class ExchangeController(private val exchangeFacade: ExchangeFacade) {

  @PatchMapping("accounts/{pesel}/exchange")
  fun exchangeCurrency(@PathVariable pesel: String, @RequestBody command: ExchangeCommand)
          = exchangeFacade.exchange(command, Pesel(pesel))

}