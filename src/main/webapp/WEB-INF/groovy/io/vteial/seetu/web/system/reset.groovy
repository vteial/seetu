package io.vteial.seetu.web.system;

import groovy.time.TimeCategory
import io.vteial.seetu.model.AccountTransaction
import io.vteial.seetu.model.Customer
import io.vteial.seetu.model.Item
import io.vteial.seetu.model.ItemTransaction
import io.vteial.seetu.model.Role
import io.vteial.seetu.model.User
import io.vteial.seetu.model.constants.AccountTransactionType
import io.vteial.seetu.model.constants.UserStatus

try {
	println 'reset started...'

	User user = new User()
	user.id = 'vteial'
	user.password = '1234'
	user.emailId = 'vteial@gmail.com'
	user.firstName = 'Eialarasu'
	user.lastName = 'VT'
	user.status = UserStatus.ACTIVE
	user.roleId = Role.FOREMAN

	// TODO : login as vteial
	User sessionUser = user

	userService.add(sessionUser, user)

	List<Long> subscriberAccountIds = []
	for(int i = 1; i < 7; i++) {
		Customer subscriber = new Customer()
		subscriber.identiyNumber = "subscriber-$i"
		subscriber.firstName = "Subscriber - $i"
		subscriber.lastName = "Subscriber - $i"

		customerService.add(sessionUser, subscriber)

		subscriberAccountIds << subscriber.accountId
	}

	Item item = new Item()
	item.name = 'Six Thousand'
	item.value = 6000
	item.opearatorCommision = 5
	item.totalSubscribers = 6
	item.biddingShield = 40
	item.biddingStartDate = new Date()

	item.subscriberIds = subscriberAccountIds

	itemService.add(sessionUser, item)

	float subscriptionAmount = item.value / item.totalSubscribers

	List<Double> winningBidAmounts = [4600, 5100, 5300, 5600, 5800, 5900]
	//List<Double> winningBidAmounts = [4600]

	Date biddingDate = new Date()
	biddingDate[Calendar.DATE] = item.biddingStartDate[Calendar.DATE]
	biddingDate[Calendar.MONTH] = item.biddingStartDate[Calendar.MONTH]
	biddingDate[Calendar.YEAR] = item.biddingStartDate[Calendar.YEAR]
	use(TimeCategory) {
		for(int j = 0; j < winningBidAmounts.size(); j++) {

			for(int i = 0; i < item.totalSubscribers; i++) {

				AccountTransaction accTran = new AccountTransaction()
				accTran.type = AccountTransactionType.DEPOSIT
				accTran.amount = subscriptionAmount
				accTran.description = 'nth subscription for the month x'
				accTran.accountId = item.subscriberIds[i]
				accountService.addTransaction(sessionUser, accTran)
			}
			ItemTransaction itemTran = new ItemTransaction()
			itemTran.itemId = item.id
			itemTran.winningBidAmount = winningBidAmounts[j]
			itemTran.winnerAccountId = item.subscriberIds[j]
			itemTran.nthBid = (j+1)
			biddingDate += 1.month
			itemTran.date = biddingDate
			itemService.addTransaction(sessionUser, itemTran)
		}
	}

	println 'reset finished...'
}
catch(Throwable t) {
	t.printStackTrace(out)
}