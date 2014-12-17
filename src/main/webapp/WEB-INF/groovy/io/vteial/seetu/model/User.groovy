package io.vteial.seetu.model;

import groovy.transform.Canonical
import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Key

@Entity(unindexed=false)
@Canonical
public class User implements Serializable {

	@Key
	String id;

	String password;

	String emailId;

	String status;

	String firstName;

	String lastName;

	Date createTime

	Date updateTime;

	transient String newPassword;

}