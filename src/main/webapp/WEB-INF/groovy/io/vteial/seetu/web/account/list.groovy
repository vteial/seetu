package io.vteial.seetu.web.account

import io.vteial.seetu.model.Account

List<Account> accounts = Account.findAll()

response.contentType = 'application/json'
jsonObjectMapper.writeValue(out, accounts)
