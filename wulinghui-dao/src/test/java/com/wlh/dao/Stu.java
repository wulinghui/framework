package com.wlh.dao;


public class Stu {

	String STUNAME;
	String EMAIL; 
	int ID;
	public String getSTUNAME() {
		return STUNAME;
	}
	public void setSTUNAME(String sTUNAME) {
		STUNAME = sTUNAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Stu(String sTUNAME, String eMAIL) {
		super();
		STUNAME = sTUNAME;
		EMAIL = eMAIL;
	}
	@Override
	public String toString() {
		return "Stu [STUNAME=" + STUNAME + ", EMAIL=" + EMAIL + ", ID=" + ID
				+ "]";
	}
	public Stu() {
		super();
	}
     
}
