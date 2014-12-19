package io.vteial.seetu.model

import groovy.transform.Canonical
import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Ignore
import groovyx.gaelyk.datastore.Key

@Entity(unindexed=false)
@Canonical
public class Account implements Serializable {

	static final String ID_KEY = "accountId"

	@Key
	long id

	String name

	String aliasName

	String type

	boolean isMinus

	double balance

	String status

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
	public boolean hasSufficientBalance(double amount) {

		if (this.isMinus) {
			return true
		}

		return this.balance >= amount
	}

	public void withdraw(double amount) {
		this.balance -= amount
	}

	public void deposit(double amount) {
		this.balance += amount
	}
}
