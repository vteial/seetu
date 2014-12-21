package io.vteial.seetu.service.impl

import groovyx.gaelyk.logging.GroovyLogger
import io.vteial.seetu.model.Account
import io.vteial.seetu.model.AccountTransaction
import io.vteial.seetu.model.Item
import io.vteial.seetu.model.ItemTransaction
import io.vteial.seetu.model.User
import io.vteial.seetu.model.constants.AccountStatus
import io.vteial.seetu.model.constants.AccountTransactionType
import io.vteial.seetu.model.constants.AccountType
import io.vteial.seetu.model.constants.ItemTransactionStatus
import io.vteial.seetu.service.AccountService
import io.vteial.seetu.service.ItemService
import io.vteial.seetu.service.exceptions.ModelAlreadyExistException

class DefaultItemService extends AbstractService implements ItemService {

	GroovyLogger log = new GroovyLogger(DefaultItemService.class.getName())

	AccountService accountService

	@Override
	public void add(User sessionUser, Item item)
	throws ModelAlreadyExistException {
		log.info "Pre-" + item.toString()

		Account account = new Account()
		account.name = "${item.name}-Commision"
		account.aliasName = "Commision-${item.name}"
		account.type = AccountType.COMMISION
		account.status = AccountStatus.ACTIVE

		accountService.add(sessionUser, account);

		item.commisionAccount = account
		item.commisionAccountId = account.id

		account = new Account()
		account.name = "${item.name}-Fund"
		account.aliasName = "Fund-${item.name}"
		account.type = AccountType.FUND
		account.status = AccountStatus.ACTIVE

		accountService.add(sessionUser, account);

		item.itemAccount = account
		item.itemAccountId = account.id

		item.user = sessionUser
		item.userId = sessionUser.id

		item.id = autoNumberService.getNextNumber(Item.ID_KEY)

		item.prePersist(sessionUser.id)
		item.save()

		log.info "Post-" + item.toString()
	}

	@Override
	public void addTransaction(User sessionUser, ItemTransaction itemTran) {
		log.info "Pre-" + itemTran.date.toString()

		itemTran.item = Item.get(itemTran.itemId)

		this.aggregateSubscriptions(sessionUser, itemTran)

		itemTran.computeDiscountAmount()
		itemTran.computeDiscountShareAmount()
		itemTran.computeCommisionAmount()
		itemTran.computeWinnderBidAmount()

		if(!itemTran.description) {
			itemTran.description = '-'
		}

		if(!itemTran.date) {
			itemTran.date = new Date()
		}

		itemTran.status = ItemTransactionStatus.COMPLETE

		itemTran.userId = sessionUser.id

		itemTran.id = autoNumberService.getNextNumber(ItemTransaction.ID_KEY)

		itemTran.prePersist(sessionUser.id)
		itemTran.save()

		log.info "Post-" + itemTran.date.toString()

		this.payCommisionAmount(sessionUser, itemTran)

		this.payBidWinningAmount(sessionUser, itemTran)

		this.payDiscountShares(sessionUser, itemTran)
	}


	void aggregateSubscriptions(User sessionUser, ItemTransaction itemTran) {
		float subscriptionAmount = itemTran.item.value / itemTran.item.totalSubscribers

		for(int i = 0; i < itemTran.item.totalSubscribers; i++) {

			AccountTransaction accTran = new AccountTransaction()
			accTran.type = AccountTransactionType.WITHDRAW
			accTran.amount = subscriptionAmount
			accTran.description = 'nth subscription for the month x'
			accTran.accountId = itemTran.item.subscriberIds[i]
			accountService.addTransaction(sessionUser, accTran)

			accTran = new AccountTransaction()
			accTran.type = AccountTransactionType.DEPOSIT
			accTran.amount = subscriptionAmount
			accTran.description = 'nth subscription for the month x'
			accTran.accountId = itemTran.item.itemAccountId
			accountService.addTransaction(sessionUser, accTran)
		}
	}

	void payCommisionAmount(User sessionUser, ItemTransaction itemTran) {
		AccountTransaction accTran = new AccountTransaction()
		accTran.type = AccountTransactionType.WITHDRAW
		accTran.amount = itemTran.commisionAmount
		accTran.description = 'nth commision for the month x'
		accTran.accountId = itemTran.item.itemAccountId
		accountService.addTransaction(sessionUser, accTran)

		accTran = new AccountTransaction()
		accTran.type = AccountTransactionType.DEPOSIT
		accTran.amount = itemTran.commisionAmount
		accTran.description = 'nth commision for the month x'
		accTran.accountId = itemTran.item.commisionAccountId
		accountService.addTransaction(sessionUser, accTran)
	}

	void payBidWinningAmount(User sessionUser, ItemTransaction itemTran) {
		AccountTransaction accTran = new AccountTransaction()
		accTran.type = AccountTransactionType.WITHDRAW
		accTran.amount = itemTran.winnerBidAmount
		accTran.description = 'nth winner bid amount for the month x'
		accTran.accountId = itemTran.item.itemAccountId
		accountService.addTransaction(sessionUser, accTran)

		accTran = new AccountTransaction()
		accTran.type = AccountTransactionType.DEPOSIT
		accTran.amount = itemTran.winnerBidAmount
		accTran.description = 'nth winner bid amount for the month x'
		accTran.accountId = itemTran.winnerAccountId
		accountService.addTransaction(sessionUser, accTran)
	}

	void payDiscountShares(User sessionUser, ItemTransaction itemTran) {
		for(int i = 0; i < itemTran.item.totalSubscribers; i++) {
			AccountTransaction accTran = new AccountTransaction()
			accTran.type = AccountTransactionType.WITHDRAW
			accTran.amount = itemTran.discountShareAmount
			accTran.description = 'nth discount share for the month x'
			accTran.accountId = itemTran.item.itemAccountId
			accountService.addTransaction(sessionUser, accTran)

			accTran = new AccountTransaction()
			accTran.type = AccountTransactionType.DEPOSIT
			accTran.amount = itemTran.discountShareAmount
			accTran.description = 'nth discount share for the month x'
			accTran.accountId = itemTran.item.subscriberIds[i]
			accountService.addTransaction(sessionUser, accTran)
		}
	}
}
