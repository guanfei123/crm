package com.offcn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.offcn.bean.SalesChance;

public interface SalesChanceMapper {

	

	void delete(Integer id);

	int add(SalesChance saleschance);

	public Integer gettotalRecord(Map<String, Object> map);

	List<SalesChance> getPageList(Map<String, Object> map);

	SalesChance getScsById(Integer id);

	int update(SalesChance saleschance);

	SalesChance getUserById(Integer id);

	int assign(SalesChance saleschance);



	

	
}
