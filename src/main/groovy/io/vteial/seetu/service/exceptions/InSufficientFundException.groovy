package io.vteial.seetu.service.exceptions

import groovy.transform.Canonical
import io.vteial.seetu.model.Account

@Canonical
class InSufficientFundException extends Exception {

	Account account
}
