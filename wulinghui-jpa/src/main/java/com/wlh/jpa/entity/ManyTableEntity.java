package com.wlh.jpa.entity;

/**
 * @author wulinghui
 * 多对多的实体
 */
public class ManyTableEntity extends JpaEntity{
	Class<? extends JpaEntity> oneTable;
	Class<? extends JpaEntity> twoTable;
	public ManyTableEntity( Class<? extends JpaEntity> oneTable,
			Class<? extends JpaEntity> twoTable) {
		this.oneTable = oneTable;
		this.twoTable = twoTable;
	}
	public Class<? extends JpaEntity> getOneTable() {
		return oneTable;
	}
	public Class<? extends JpaEntity> getTwoTable() {
		return twoTable;
	}
}