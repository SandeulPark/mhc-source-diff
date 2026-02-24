package kr.go.mhc.mhcapp.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.AfterServeyService;

@Controller
@RequestMapping(value="/cmmn")
public class AfterServeyController extends DMultiActionController{
	
	@Resource(name="mhcapp.sv.AfterServeyService")
	private AfterServeyService afterServeyService;
	
	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@RequestMapping( value="/afterServey.do", method = RequestMethod.GET)
	public String afterServey(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
				
		String rtnPage = "app/cm/afterServeyPersonalInfo";
		
		return rtnPage;
	}	
	
	
	@RequestMapping( value="/afterServeyMain.do", method = RequestMethod.POST)
	public ModelAndView afterServeyMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		 ModelAndView modelAndView = new ModelAndView();
		  modelAndView.setViewName("app/cm/afterServey");
		  modelAndView.addObject("answrSn",param.get("answrSn").toString());
		  modelAndView.addObject("flag","4");
		  modelAndView.addObject("isDo", "0");
		return modelAndView;
	}	
	

	@RequestMapping( value="/afterServeyAnswrInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> afterServeyMasterInsert(@ModelAttribute Map param, ModelMap model) throws Exception{
		 new HashMap<String,Object>();
		
		 Map<String,Object> rsMap = new HashMap<String,Object>(); 
		 
		 if(param.get("personalInfoBgn").equals("Y")){
			 rsMap = afterServeyService.insertafterServeyAnswr(param); 
		}else{
			rsMap.put("personalInfoBgn", "return");
		}
			
		return rsMap;
	}
	
	@RequestMapping( value="/afterServeyCodeList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> afterServeyCodeList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = afterServeyService.selectAfterServeyCodeList(param);   
		rsMap.put("rsList", rsList);
		
		return rsMap;
	}	
	
	@RequestMapping( value="/afterServeyList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> afterServeyList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = afterServeyService.selectAfterServeyList(param);   
		rsMap.put("rsList", rsList);	
		
		return rsMap;
	}
	
	@RequestMapping( value="/afterServeyAnswrUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> afterServeyAnswrUpdate(@ModelAttribute Map param, ModelMap model) throws Exception{
		 Map<String,Object> rsMap = new HashMap<String,Object>(); 
		 int rsInt = afterServeyService.updateAfterServeyAnswr(param); 
		 rsMap.put("rsCount", rsInt);
		return rsMap;
	}
}
