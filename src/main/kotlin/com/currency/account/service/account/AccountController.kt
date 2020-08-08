package com.currency.account.service.account

import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal


@RestController
@RequestMapping("/account")
class AccountController(private val accountFacade: AccountFacade) {

  @ApiOperation(value = "Mark lines as already sent to warehouse (not in store)")
  @ApiResponses(value = [
    ApiResponse(code = 200, message = "Success"),
    ApiResponse(code = 409, message = "Account with this pesel currently exists")
  ])
  @PostMapping("/account")
  fun createAccount(@RequestBody commandDto: CreateAccountCommandDto) {
    accountFacade.createAccount(commandDto.toCommand())
  }

}

data class CreateAccountCommandDto(
        private val firstName: String,
        private val lastName: String,
        private val pesel: String,
        @ApiModelProperty(notes = "PLN")
        private val balance: BigDecimal
) {
  fun toCommand() = CreateAccountCommand(firstName,lastName, Pesel(pesel), balance)
}