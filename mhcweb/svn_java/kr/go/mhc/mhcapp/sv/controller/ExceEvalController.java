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

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.ExceEvalService;

@Controller
public class ExceEvalController extends DMultiActionController{ 
	
	@Resource(name="mhcapp.sv.ExceEvalService")
	private ExceEvalService exceEvalService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	

	/**
	 * 운동평가결과 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exceEvalMain.do", method = RequestMethod.GET)
	public String rankMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		return "app/sv/exceEval";
	}	
	
	/**
	 * 운동평가결과1 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exceEvalList1.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = exceEvalService.selectExceEvalList1(param);   

		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	/**
	 * 운동평가결과1-1 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exceEvalList1_1.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList1_1(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = exceEvalService.selectExceEvalList1_1(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
	/**
	 * 운동평가결과2 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exceEvalList2.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList2(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = exceEvalService.selectExceEvalList2(param);   
		List<Map<String,String>> rsList2 = exceEvalService.selectSignLoad(param);   
		
		rsMap_sb.put("data", rsList);
		rsMap_sb.put("sign", rsList2);
		
		rsMap.put("rsList", rsMap_sb);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
}
