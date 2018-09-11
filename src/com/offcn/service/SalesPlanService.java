package com.offcn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.bean.SalesChance;
import com.offcn.dao.SalesPlanMapper;
import com.offcn.page.Page;

@Service
public class SalesPlanService {

	@Autowired
	private SalesPlanMapper spm;
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(Map<String, Object> map){
		Page<SalesChance> page=null;
		Integer pageNum=1;
		try {
			
			pageNum=Integer.parseInt((String)map.get("pageNo"));
		} catch (Exception e) {
		}
		//获取总记录数
		int totalRecord=spm.gettotalRecord(map);
		int pageSize = (int)map.get("pageSize");
		page=new Page<SalesChance>(totalRecord, pageSize, pageNum);
		int first=page.getIndex()+1;
		int end=first+pageSize; 
		map.put("first",first);
		map.put("end",end);
		List<SalesChance> list=spm.getPageList(map);
		page.setList(list);
		return page;
	}

	public SalesChance getScById(Integer id) {
		// TODO Auto-generated method stub
		return spm.getScById(id);
	}
}
