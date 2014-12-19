package io.vteial.seetu.model

import groovy.transform.Canonical
import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Ignore
import groovyx.gaelyk.datastore.Key

@Entity(unindexed=false)
@Canonical
public class ItemTransaction implements Serializable {

	static final String ID_KEY = "itemTranId"

	@Key
	long id

	double winningBidAmount

	double commisionAmount

	double winnerBidAmount

	double discountAmount

	double discountShareAmount

	String description

	Date date

	String status

	long winnerAccountId

	@Ignore
	Account winnerAccount

	long itemId

	@Ignore
	Item item

	String userId

	@Ignore
	User user

	String createBy

	String updateBy

	Date createTime

	Date updateTime

	void preUpdate(String updateBy) {
		this.updateBy = updateBy
		this.updateTime = new Date()
	}

	void prePersist(String createAndUpdateBy) {
		this.createBy = createAndUpdateBy
		this.updateBy = createAndUpdateBy
		Date now = new Date()
		this.createTime = now;
		this.updateTime = now;
	}

	// Domain Operations
	void computeDiscountAmount() {
		this.discountAmount = this.item.value - this.winningBidAmount
	}

	void computeDiscountShareAmount() {
		this.discountShareAmount = this.discountAmount / this.item.totalSubscribers
		this.discountShareAmount = Math.round(this.discountShareAmount * 100) / 100
	}

	void computeCommisionAmount() {
		this.commisionAmount = (this.item.value * this.item.opearatorCommision) / 100
		this.commisionAmount = Math.round(this.commisionAmount * 100) / 100
	}

	void computeWinnderBidAmount() {
		this.winnerBidAmount = this.winningBidAmount - this.commisionAmount
	}

}
