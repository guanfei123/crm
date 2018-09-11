package com.offcn.dao;

import java.util.List;
import java.util.Map;

import com.offcn.bean.User;

public interface UserMapper {

	User login(String username);

	//获取总个数
	Integer gettotalRecord(Map<String, Object> map);

	
	List<User> getPageList(Map<String, Object> map);

	List<User> getName();

}
