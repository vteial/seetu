package io.vteial.seetu.model;

import groovy.transform.Canonical
import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Key

@Entity(unindexed=false)
@Canonical
public class Country implements Serializable {

	@Key
	String id

	String twoLetterCode

	String threeLetterCode

	int numericCode

	String name

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
