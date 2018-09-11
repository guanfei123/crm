package com.offcn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.bean.SalesChance;
import com.offcn.dao.SalesChanceMapper;
import com.offcn.page.Page;

@Service
public class SalesChanceService {

	@Autowired
	private SalesChanceMapper scm;
	

	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		scm.delete(id);
	}
	@Transactional
	public int add(SalesChance saleschance) {
		// TODO Auto-generated method stub
		return scm.add(saleschance);
	}
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(Map<String, Object> map){
		Page<SalesChance> page=null;
		Integer pageNum=1;
		try {
			
			pageNum=Integer.parseInt((String)map.get("pageNo"));
		} catch (Exception e) {
		}
		//获取总记录数
		int totalRecord=scm.gettotalRecord(map);
		int pageSize = (int)map.get("pageSize");
		page=new Page<SalesChance>(totalRecord, pageSize, pageNum);
		int first=page.getIndex()+1;
		int end=first+pageSize; 
		map.put("first",first);
		map.put("end",end);
		List<SalesChance> list=scm.getPageList(map);
		page.setList(list);
		return page;
	}
	public SalesChance getScsById(Integer id) {
		// TODO Auto-generated method stub
		return scm.getScsById(id);
	}
	public int update(SalesChance saleschance) { 
		// TODO Auto-generated method stub
		return scm.update(saleschance);
	}
	public SalesChance getUserById(Integer id) {
		// TODO Auto-generated method stub
		return scm.getUserById(id);
	}
	public int assign(SalesChance saleschance) {
		// TODO Auto-generated method stub
		return scm.assign(saleschance);
	}




}
