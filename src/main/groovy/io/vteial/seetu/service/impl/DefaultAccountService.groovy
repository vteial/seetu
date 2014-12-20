package io.vteial.seetu.service.impl

import groovyx.gaelyk.logging.GroovyLogger
import io.vteial.seetu.model.Account
import io.vteial.seetu.model.AccountTransaction
import io.vteial.seetu.model.User
import io.vteial.seetu.model.constants.AccountTransactionStatus
import io.vteial.seetu.model.constants.AccountTransactionType
import io.vteial.seetu.service.AccountService
import io.vteial.seetu.service.exceptions.InSufficientFundException
import io.vteial.seetu.service.exceptions.ModelAlreadyExistException

class DefaultAccountService extends AbstractService implements AccountService {

	GroovyLogger log = new GroovyLogger(DefaultAccountService.class.getName())

	@Override
	public void add(User sessionUser, Account account)
	throws ModelAlreadyExistException {
		log.info "Pre-Account[id = ${account.id}, name = ${account.name}, userId = ${account.userId}]"

		account.user   = sessionUser
		account.userId = sessionUser.id

		account.id = autoNumberService.getNextNumber(Account.ID_KEY)

		account.prePersist(sessionUser.id)
		account.save()

		log.info "Post-Account[id = ${account.id}, name = ${account.name}, userId = ${account.userId}]"
	}

	@Override
	public void addTransaction(User sessionUser, AccountTransaction accountTran) throws InSufficientFundException {
		log.info "Pre-AccoutTransaction[id=${accountTran.id}, amount=${accountTran.amount}, balance=${accountTran.accountId}, accountId=${accountTran.accountId}, userId=${accountTran.userId}]"

		Account account = Account.get(accountTran.accountId)

		if (accountTran.type == AccountTransactionType.WITHDRAW && !account.hasSufficientBalance(accountTran.amount)) {
			throw new InSufficientFundException(accountTran, account);
		}

		if(accountTran.type == AccountTransactionType.WITHDRAW) {
			account.withdraw(accountTran.amount)
		} else {
			account.deposit(accountTran.amount)
		}

		account.preUpdate(sessionUser.id)
		account.save()

		accountTran.balance = account.balance

		if(!accountTran.description) {
			accountTran.description = '-'
		}

		if(!accountTran.date) {
			accountTran.date = new Date()
		}

		accountTran.status = AccountTransactionStatus.COMPLETE

		accountTran.userId = sessionUser.id

		accountTran.id = autoNumberService.getNextNumber(AccountTransaction.ID_KEY)

		accountTran.prePersist(sessionUser.id)
		accountTran.save()

		log.info "Post-AccoutTransaction[id=${accountTran.id}, amount=${accountTran.amount}, balance=${accountTran.accountId}, accountId=${accountTran.accountId}, userId=${accountTran.userId}]"
	}
}
