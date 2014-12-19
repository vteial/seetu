package io.vteial.seetu.model;

import groovy.transform.Canonical

import groovy.transform.Canonical
import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Key

@Entity(unindexed=false)
@Canonical
public class Address implements Serializable {

	final String ID_KEY = "addressId"

	@Key
	long id

	String address

	String cityOrTown

	String postalCode

	String stateCode

	String countryCode

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
