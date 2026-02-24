package kr.go.mhc.mhcapp.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.NutEvalService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NutEvalController extends DMultiActionController{ 
	@Resource(name="mhcapp.sv.NutEvalService")
	private NutEvalService nutEvalService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	

	/**
	 * 영양평가결과 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalMain.do", method = RequestMethod.GET)
	public String rankMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		return "app/sv/nutEval";
	}	
	
	/**
	 * 영양평가결과1 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalList1.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = nutEvalService.selectNutEvalList1(param);   

		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
	/**
	 * 영양평가결과2 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalList2.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList2(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = nutEvalService.selectNutEvalList2(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
	/**
	 * 영양평가결과3 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalList3.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList3(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = nutEvalService.selectNutEvalList3(param);   
		List<Map<String,String>> rsList2 = nutEvalService.selectSignLoad(param);  
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
	
	/**
	 * 서명 이미지 sn 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/signLoad.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList4(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = nutEvalService.selectSignLoad(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	

	/**
	 * 영양평가 좋아요 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/nutEvalLikeUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateNutEvalLike(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		int rsList = nutEvalService.updateNutEvalLike(param);   
		
		rsMap.put("rsList", rsList);
		/*if(rsCount>0){
			//rsMap.put("msg", getMsg("common.write.succ"));
		}else{
			//rsMap.put("msg", getMsg("common.write.err"));
		}*/
		return rsMap;
	}	
}
