package com.offcn.controller;
/**
 * 客户开发计划
 * 
 * 
 */
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.offcn.bean.SalesChance;
import com.offcn.page.Page;
import com.offcn.service.SalesPlanService;

@Controller
@RequestMapping("/plan")
public class SalesPlanController {
	private int pageSize=2;
	@Autowired
	private SalesPlanService sps;

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView getSc(@RequestParam(value="pageNo",required= false,defaultValue="1")String pageNo,HttpServletRequest request) {
		String substring = "";
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "search_");	
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);	
		ModelAndView mv = new ModelAndView("/plan/list");
		
		String requestURI = request.getRequestURI();
		if(requestURI.contains("?")) {
			int indexOf = requestURI.indexOf("?");
			substring = requestURI.substring(0, indexOf);
		}else {
			substring=requestURI;
		}		
		String queryString=parameterMapToString(map);
        Page<SalesChance> page = sps.getPage(map);
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
	@RequestMapping(value="/tomake",method=RequestMethod.GET)
	public String drawPlan(ModelMap model,Integer id) {
		SalesChance saleschance=sps.getScById(id);
		model.addAttribute("saleschance", saleschance);
		return "plan/make";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String add(String date,String todo) {
		return null;
	}
	
	
	
	
	
	
	
	
	
	
}
