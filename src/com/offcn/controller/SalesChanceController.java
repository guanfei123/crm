package com.offcn.controller;


import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.offcn.bean.SalesChance;
import com.offcn.bean.User;
import com.offcn.page.Page;
import com.offcn.service.SalesChanceService;
import com.offcn.service.UserService;
/**
 * 
 * 营销机会管理页面
 * 指派        添加       删除        查询
 */
@Controller
@RequestMapping("/chance")
public class SalesChanceController {
	private int pageSize=2;
	@Autowired
	private SalesChanceService scs;
	@Autowired
	private UserService us;
	//查询
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView getSc(@RequestParam(value="pageNo",required= false,defaultValue="1")String pageNo,HttpServletRequest request) {
		String substring = "";
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "search_");	
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);	
		ModelAndView mv = new ModelAndView("/saleschance/list");
		//获取请求参数的URI  ,
		String requestURI = request.getRequestURI();
		if(requestURI.contains("?")) {
			int indexOf = requestURI.indexOf("?");
			substring = requestURI.substring(0, indexOf);
		}else {
			substring=requestURI;
		}
		//查询时所带的参数
		String queryString=parameterMapToString(map);
		
        Page<SalesChance> page = scs.getPage(map);
        page.setPath(substring);
        page.setQueryString(queryString);
		mv.addObject("page", page);
		
		
		return mv;
	}
	//将map集合数据 转换成字符串
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
	//删除
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	public String delete(Integer id) { 
		scs.delete(id);
		return "redirect:/chance/list";
	}
	//到达添加页面
	@RequestMapping(value="/toadd",method=RequestMethod.GET)
	public ModelAndView toadd() {
		ModelAndView mv=new ModelAndView("/saleschance/add");
		SalesChance saleschance=new SalesChance();
		mv.addObject("saleschance", saleschance);
		return mv;
	}
	//提交添加
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(SalesChance saleschance) {
		System.out.println(saleschance);
		int result=scs.add(saleschance);
		if(result==1) {
			return "redirect:/chance/list";
		}
		return "redirect:/chance/toadd";
	}
	//到达修改
	@RequestMapping(value="/toupdate",method=RequestMethod.GET)
	public String toupdate(ModelMap model,Integer id) {
		SalesChance saleschance=scs.getScsById(id);
		model.addAttribute("saleschance", saleschance);
		return "saleschance/add";
	}
	//修改
	@RequestMapping(value="/add",method=RequestMethod.PUT)
	public String update(SalesChance saleschance) {
		int result=scs.update(saleschance);
		if(result==1) {
			return "redirect:/chance/list";
		}
		return "redirect:/chance/toupdate"+saleschance.getId();
	}
	//到达指派
	@RequestMapping(value="toassign",method=RequestMethod.GET)
	public String toassign(ModelMap model,Integer id) {
		SalesChance saleschance=scs.getUserById(id);
		List<User> list= us.getName();
		model.addAttribute("list", list);
		model.addAttribute("saleschance", saleschance);
		return "saleschance/assign";
	}
	//指派
	@RequestMapping(value="/assign",method=RequestMethod.PUT)
	public String assign(SalesChance saleschance) {
		int result=scs.assign(saleschance);
		if(result==1) {
			return "redirect:/chance/list";
		}
		return "redirect:/chance/toassign"+saleschance.getId();
	}
}
