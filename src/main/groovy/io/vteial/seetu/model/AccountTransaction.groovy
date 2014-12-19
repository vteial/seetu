package io.vteial.seetu.model

import groovy.transform.Canonical
import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Ignore
import groovyx.gaelyk.datastore.Key

@Entity(unindexed=false)
@Canonical
public class AccountTransaction implements Serializable {

	static final String ID_KEY = "accountTranId"

	@Key
	long id

	String type

	double amount

	String description

	Date date

	double balance

	String status

	long accountId

	@Ignore
	Account account

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
}
