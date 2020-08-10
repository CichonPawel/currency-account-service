package com.currency.account.service.exchange

import com.currency.account.service.pesel.Pesel
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/exchange")
class ExchangeController(private val exchangeFacade: ExchangeFacade) {


  @ApiOperation(value = "exchange currency")
  @ApiResponses(value = [
    ApiResponse(code = 200, message = "Success"),
    ApiResponse(code = 409, message = "Account with this pesel currently exists")
  ])
  @PatchMapping("/{pesel}")
  fun exchangeCurrency(@PathVariable pesel: String, @RequestBody command: ExchangeCommand)
          = exchangeFacade.exchange(command, Pesel(pesel))

}