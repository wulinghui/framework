package com.wlh.jpa.test;

import com.wlh.jpa.entity.JpaEntity;

public class StuendInfo extends JpaEntity {
	String ruXueShijian;//入学时间.
	String xueLi;//学历
	public String getRuXueShijian() {
		return ruXueShijian;
	}
	public void setRuXueShijian(String ruXueShijian) {
		this.ruXueShijian = ruXueShijian;
	}
	public String getXueLi() {
		return xueLi;
	}
	public void setXueLi(String xueLi) {
		this.xueLi = xueLi;
	}
	@Override
	public String toString() {
		return "StuendInfo [ruXueShijian=" + ruXueShijian + ", xueLi=" + xueLi
				+ ", id=" + id + "]";
	}
}
