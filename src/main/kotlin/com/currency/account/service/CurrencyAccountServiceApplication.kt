package com.currency.account.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class CurrencyAccountServiceApplication

fun main(args: Array<String>) {
  runApplication<CurrencyAccountServiceApplication>(*args)
}
