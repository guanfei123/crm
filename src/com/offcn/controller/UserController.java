package com.offcn.controller;
/**
 * 登录功能及展示页面
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.offcn.bean.Authority;
import com.offcn.bean.SalesChance;
import com.offcn.bean.User;
import com.offcn.page.Page;
import com.offcn.service.UserService;

import junit.test.Navigation;


@Controller
@RequestMapping("/user")
public class UserController {
	private int pageSize=2;
	@Autowired
	private UserService us;
	//登录
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,HttpSession hs,RedirectAttributes ra) {
		User user=us.login(username,password);
		if(user==null) {
			ra.addFlashAttribute("msg", "输入有误");
			return "redirect:/index";
		}
		//保存用户信息
		hs.setAttribute("user", user);
		return "home/success";
	}
	
	//用户退出
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession hs) {
		hs.removeAttribute("user");
		return "index";
	}
	@ResponseBody
	@RequestMapping(value="/menu",method=RequestMethod.GET)
	public List<Navigation> getNav(HttpSession session){
		List<Navigation> list=new ArrayList<>();
		Navigation top = new Navigation(Long.MAX_VALUE,"客户关系管理系统");
		list.add(top);
		User user= (User)session.getAttribute("user");
		Map<Long, Navigation> parentNavs=new HashMap<>();
		List<Authority> authorities = user.getRole().getAuthorities();
		String path = session.getServletContext().getContextPath();
		for (Authority authority : authorities) {
			Navigation sonNav=new Navigation(authority.getId(), authority.getDisplayName());
			sonNav.setUrl(path+authority.getUrl());
			Authority parentAuthority = authority.getParentAuthority();
			Long id = parentAuthority.getId();
			Navigation parentnav = parentNavs.get(id);
			if(parentnav==null) {
				parentnav=new Navigation(id, parentAuthority.getDisplayName());
				parentnav.setState("closed");
				parentNavs.put(id, parentnav);
				top.getChildren().add(parentnav);
			}
			parentnav.getChildren().add(sonNav);
		}
		
		return list;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView getuser(@RequestParam(value="pageNo",required= false,defaultValue="1")String pageNo,HttpServletRequest request) {
		String substring = "";
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "search_");	
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);	
		ModelAndView mv = new ModelAndView("/user/list");
		
		String requestURI = request.getRequestURI();
		if(requestURI.contains("?")) {
			int indexOf = requestURI.indexOf("?");
			substring = requestURI.substring(0, indexOf);
		}else {
			substring=requestURI;
		}
		
		String queryString=parameterMapToString(map);
		
        Page<User> page = us.getPage(map);
        page.setPath(substring);
        page.setQueryString(queryString);
		mv.addObject("page", page);
		
		
		return mv;
	}
	private String parameterMapToString(Map<String, Object> map) {
		StringBuilder str=new StringBuilder();
		for(Entry<String,Object> entry:map.entrySet()) {
			String key = entry.getKey();
			if(key.startsWith("LIKE")) {
				entry.getValue();
				str.append("&search_").append(key).append("=").append(entry.getValue());
			}
		}
		return str.toString();
	}

}
