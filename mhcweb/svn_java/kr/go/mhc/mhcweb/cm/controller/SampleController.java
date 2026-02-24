package kr.go.mhc.mhcweb.cm.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController extends DMultiActionController{ 

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	//목록조회
	@RequestMapping( value="/sample.do")
	public String sample(@ModelAttribute Map param, ModelMap model) throws Exception{
//		List<Map<String,Object>> rsList = mainService.getList(param);   
//		model.addAttribute("rsList", rsList);	
		
		
		return "web/sample";   
	}
	
	
	
}
