package com.wlh.jpa.entity;

import java.io.Serializable;

import com.wlh.jpa.PrimaryKeyPolicy;

public abstract class JpaEntity implements Serializable,Cloneable{
	
	protected String id;
	public JpaEntity(String id) {
		super();
		this.id = id;
	}
	public JpaEntity(int id) {
		this( String.valueOf(id) );
	}
	
	public JpaEntity() {
		id = PrimaryKeyPolicy.getPolicy().getId(this.getClass());
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
