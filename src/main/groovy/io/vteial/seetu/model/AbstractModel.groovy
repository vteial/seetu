package io.vteial.seetu.model;

import groovy.transform.Canonical
import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Key

@Entity(unindexed=false)
@Canonical
public class AbstractModel implements Serializable {

	String createBy

	String updateBy

	Date createTime

	Date updateTime

	void preUpdate() {
		this.updateTime = new Date()
	}

	void prePersist() {
		Date now = new Date()
		this.createTime = now;
		this.updateTime = now;
	}
}