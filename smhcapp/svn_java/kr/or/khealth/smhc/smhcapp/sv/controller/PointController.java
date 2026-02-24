package kr.or.khealth.smhc.smhcapp.sv.controller;

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

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcapp.sv.service.PointService;

@Controller
@RequestMapping(value="/smhcapp/sv")
public class PointController extends DMultiActionController{ 

	@Resource(name="smhcapp.sv.PointService")
	private PointService pointService;
	
	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@RequestMapping( value="/selectMissionPoint.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> selectMissionPoint(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		List<Map<String, String>> rsList = pointService.selectMissionPoint(param); 
		return rsList;
	}
	
	@RequestMapping( value="/selectPointList.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> selectPointList(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		List<Map<String, String>> rsList = pointService.selectPointList(param); 
		return rsList;
	}
}
