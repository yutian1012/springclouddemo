package org.sjq.test.service;

import java.util.List;

import org.sjq.test.entities.Dept;

public interface DeptService {
	public boolean add(Dept dept);
	
	public Dept get(Long id);
	
	public List<Dept> list();
}
