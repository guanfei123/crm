package com.offcn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offcn.bean.SalesChance;
import com.offcn.bean.User;
import com.offcn.dao.UserMapper;
import com.offcn.page.Page;

@Service
public class UserService {
	@Autowired
	private UserMapper um;

	@Transactional(readOnly=true)
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User user =um.login(username);
		if(username!=null&&user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Transactional(readOnly=true)
	public Page<User> getPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Page<User> page=null;
		Integer pageNum=1;
		try {			
			pageNum=Integer.parseInt((String)map.get("pageNo"));
		} catch (Exception e) {
		}
		//获取总记录数
		int totalRecord=um.gettotalRecord(map);
		int pageSize = (int)map.get("pageSize");
		page=new Page<User>(totalRecord, pageSize, pageNum);
		int first=page.getIndex()+1;
		int end=first+pageSize; 
		map.put("first",first);
		map.put("end",end);
		List<User> list=um.getPageList(map);
		page.setList(list);
		return page;
	}

	public List<User> getName() {
		// TODO Auto-generated method stub
		return um.getName();
	}



}
