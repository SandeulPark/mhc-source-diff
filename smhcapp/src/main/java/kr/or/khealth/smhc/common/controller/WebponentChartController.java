package kr.or.khealth.smhc.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.service.WebponentChartService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebponentChartController extends DMultiActionController{ 
	@Resource(name="webponentChartService")
	private WebponentChartService chartService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	//목록조회
	@RequestMapping( value="/testMobileChart.do")
	public String sampleMobileChart(@ModelAttribute Map param, ModelMap model) throws Exception{
		return "app/sample/sampleChart";   
	}	

	//목록조회
	@RequestMapping( value="/testMobileChart_chartData.do")
	public @ResponseBody Map<String, Object> testMobileChart_chartData(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String,Object>();
		rsMap.put("result", chartService.getChartData(param));
		return rsMap;   
	}	

}
