package com.wlh.jpa;

import com.wlh.jpa.entity.JpaEntity;


public class PrimaryKeyPolicy {
	private static PrimaryKeyPolicy policy = new PrimaryKeyPolicy();
	public String getId(Class<? extends JpaEntity> cla){
		// 默认时间戳。
		return String.valueOf( System.currentTimeMillis() );
	}
	
	public static PrimaryKeyPolicy getPolicy() {
		return policy;
	}
	public static void setPolicy(PrimaryKeyPolicy policy) {
		PrimaryKeyPolicy.policy = policy;
	}
}
