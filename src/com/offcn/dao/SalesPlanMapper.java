package com.offcn.dao;

import java.util.List;
import java.util.Map;

import com.offcn.bean.SalesChance;

public interface SalesPlanMapper {

	int gettotalRecord(Map<String, Object> map);

	List<SalesChance> getPageList(Map<String, Object> map);

	SalesChance getScById(Integer id);

}
