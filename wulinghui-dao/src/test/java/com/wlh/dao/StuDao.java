package com.wlh.dao;

import java.util.List;
import java.util.concurrent.Future;

public interface StuDao {
	public List<Stu> find();
	public List<Stu> findByStuname(String name);
	public Future<List<Stu>> findByEmail(String name);
	public Stu findById(int id);
}
