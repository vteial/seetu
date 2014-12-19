package io.vteial.seetu.model

import groovy.transform.Canonical
import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Ignore
import groovyx.gaelyk.datastore.Key

@Entity(unindexed=false)
@Canonical
public class Item implements Serializable {

	static final String ID_KEY = "itemId"

	@Key
	long id

	String name

	int value

	float opearatorCommision

	int totalSubscribers

	float biddingShield

	int biddingDayOfMonth

	Date biddingStartDate

	String status

	long itemAccountId

	@Ignore
	Account itemAccount

	long commisionAccountId

	@Ignore
	Account commisionAccount

	String userId

	@Ignore
	User user

	List<Long> subscriberIds = []

	@Ignore
	List<Customer> subscribers = []

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
}
